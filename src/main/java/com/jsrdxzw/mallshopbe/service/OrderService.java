package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.biz.coupon.CouponChecker;
import com.jsrdxzw.mallshopbe.biz.coupon.ICouponDiscount;
import com.jsrdxzw.mallshopbe.biz.order.OrderPreChecker;
import com.jsrdxzw.mallshopbe.bo.OrderListenerBo;
import com.jsrdxzw.mallshopbe.bo.OrderSkuBo;
import com.jsrdxzw.mallshopbe.core.LocalUserFactory;
import com.jsrdxzw.mallshopbe.core.enumeration.CouponStatus;
import com.jsrdxzw.mallshopbe.core.enumeration.OrderStatus;
import com.jsrdxzw.mallshopbe.core.properties.MallOrderProperty;
import com.jsrdxzw.mallshopbe.dto.OrderDTO;
import com.jsrdxzw.mallshopbe.dto.SkuInfoDTO;
import com.jsrdxzw.mallshopbe.exception.ForbiddenException;
import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.exception.ParameterException;
import com.jsrdxzw.mallshopbe.exception.ServerErrorException;
import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.model.Order;
import com.jsrdxzw.mallshopbe.model.Sku;
import com.jsrdxzw.mallshopbe.model.UserCoupon;
import com.jsrdxzw.mallshopbe.repository.CouponRepository;
import com.jsrdxzw.mallshopbe.repository.OrderRepository;
import com.jsrdxzw.mallshopbe.repository.SkuRepository;
import com.jsrdxzw.mallshopbe.repository.UserCouponRepository;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private MallOrderProperty mallOrderProperty;

    @Autowired
    private SkuService skuService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private ICouponDiscount couponDiscount;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private Producer<OrderListenerBo> mallOrderProducer;

    public OrderPreChecker preCheck(Long uid, OrderDTO orderDTO) {
        List<Long> skuIdList = orderDTO.getSkuInfoList().stream()
                .map(SkuInfoDTO::getId).collect(Collectors.toList());
        List<Sku> skuList = skuService.getSkuListByIds(skuIdList);
        Long couponId = orderDTO.getCouponId();
        CouponChecker couponChecker = null;
        if (Objects.nonNull(couponId)) {
            Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new NotFoundException(40004));
            Optional<UserCoupon> userCoupon = userCouponRepository
                    .findByCouponIdAndUserIdAndStatus(couponId, uid, CouponStatus.AVAILABLE);
            if (userCoupon.isEmpty()) {
                throw new NotFoundException(50006);
            }
            couponChecker = new CouponChecker(coupon, couponDiscount);
        }
        OrderPreChecker orderPreChecker = new OrderPreChecker(orderDTO, skuList, couponChecker, mallOrderProperty.getMaxSkuLimit());
        return orderPreChecker.isOk();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Long uid, OrderDTO orderDTO, OrderPreChecker orderPreChecker) {
        String orderNo = CommonUtil.generateOrderNum();
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.add(Calendar.SECOND, mallOrderProperty.getPayTimeLimit());
        Date expireTime = DateUtils.addSeconds(now, mallOrderProperty.getPayTimeLimit());
        Order order = Order.builder()
                .orderNo(orderNo)
                .totalPrice(orderDTO.getTotalPrice())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .userId(uid)
                .totalCount(orderPreChecker.getTotalCount())
                .snapImg(orderPreChecker.getLeaderImg())
                .snapTitle(orderPreChecker.getLeaderTitle())
                .status(OrderStatus.UNPAID)
                .placedTime(now)
                .expiredTime(expireTime)
                .snapAddress(orderDTO.getAddress())
                .snapItems(orderPreChecker.getOrderSkuList())
                .build();
        orderRepository.save(order);
        reduceSkuStock(orderPreChecker);
        // 加入延时队列
        Long couponId = null;
        if (Objects.nonNull(orderDTO.getCouponId())) {
            // 核销优惠券
            writeOffCoupon(orderDTO.getCouponId(), order.getId(), uid);
            couponId = orderDTO.getCouponId();
        }
        // 进入延迟队列，可以使用redis或者MQ实现延迟队列
        try {
            mallOrderProducer.newMessage()
                    .deliverAfter(mallOrderProperty.getPayTimeLimit(), TimeUnit.SECONDS)
                    .value(OrderListenerBo.builder().oid(order.getId()).uid(uid).couponId(couponId).build())
                    .send();
        } catch (PulsarClientException e) {
            log.error("send order to pulsar error, order:{}, e:{}", order, e);
            throw new ServerErrorException();
        }
        return order.getId();
    }

    private void writeOffCoupon(Long couponId, Long oid, Long uid) {
        int result = userCouponRepository.writeOffCoupon(couponId, oid, uid);
        if (result != 1) {
            throw new ForbiddenException(40012);
        }
    }

    private void reduceSkuStock(OrderPreChecker orderPreChecker) {
        List<OrderSkuBo> skuList = orderPreChecker.getOrderSkuList();
        for (OrderSkuBo sku : skuList) {
            int result = skuRepository.reduceStock(sku.getId(), sku.getCount());
            if (result != 1) {
                throw new ParameterException(50003);
            }
        }
    }

    public Order getOrderDetail(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException(50009));
    }

    public Page<Order> getUnpaidOrder(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByExpiredTimeGreaterThanAndStatusAndUserIdOrderByCreateTimeDesc(
                new Date(), OrderStatus.UNPAID, LocalUserFactory.getUser().getId(), pageable);
    }

    public Page<Order> getOrderByStatus(int status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Long userId = LocalUserFactory.getUser().getId();
        OrderStatus orderStatus = OrderStatus.fromStatus(status);
        if (orderStatus == OrderStatus.All) {
            return orderRepository.findByUserIdOrderByCreateTimeDesc(userId, pageable);
        }
        return orderRepository.findByUserIdAndStatus(userId, orderStatus, pageable);
    }
}
