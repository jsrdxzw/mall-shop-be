package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.biz.OrderChecker;
import com.jsrdxzw.mallshopbe.dto.OrderDTO;
import com.jsrdxzw.mallshopbe.dto.SkuInfoDTO;
import com.jsrdxzw.mallshopbe.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Service
public class OrderService {
    @Value("${mall.order.pay-time-limit}")
    private Integer payTimeLimit;

    @Autowired
    private SkuService skuService;

    public OrderChecker preCheck(Long uid, OrderDTO orderDTO) {
        List<Long> skuIdList = orderDTO.getSkuInfoList().stream()
                .map(SkuInfoDTO::getId).collect(Collectors.toList());
        List<Sku> skuList = skuService.getSkuListByIds(skuIdList);

//        String orderNo = CommonUtil.generateOrderNum();
//        Calendar calendar = Calendar.getInstance();
//        Date now = new Date();
//        calendar.add(Calendar.SECOND, payTimeLimit);
//        Date expireTime = DateUtils.addSeconds(now, payTimeLimit);
//        Order order = Order.builder()
//                .orderNo(orderNo)
//                .totalPrice(orderDTO.getTotalPrice())
//                .finalTotalPrice(orderDTO.getFinalTotalPrice())
//                .userId(uid)
//                .totalCount()
//                .placedTime(now)
//                .expiredTime(expireTime)
//                .build();
//        return null;
    }
}
