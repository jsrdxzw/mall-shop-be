package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.bo.OrderListenerBo;
import com.jsrdxzw.mallshopbe.core.enumeration.OrderStatus;
import com.jsrdxzw.mallshopbe.exception.ServerErrorException;
import com.jsrdxzw.mallshopbe.model.Order;
import com.jsrdxzw.mallshopbe.repository.OrderRepository;
import com.jsrdxzw.mallshopbe.repository.UserCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Service
public class CouponBackService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserCouponRepository userCouponRepository;

    @Transactional(rollbackFor = Exception.class)
    public void returnCoupon(OrderListenerBo orderListenerBo) {
        Long couponId = orderListenerBo.getCouponId();
        if (Objects.isNull(couponId)) {
            return;
        }
        Order order = orderRepository.findById(orderListenerBo.getOid()).orElseThrow(ServerErrorException::new);
        if (order.getStatus() == OrderStatus.UNPAID || order.getStatus() == OrderStatus.CANCELED) {
            userCouponRepository.returnBack(couponId, orderListenerBo.getUid());
        }
    }
}
