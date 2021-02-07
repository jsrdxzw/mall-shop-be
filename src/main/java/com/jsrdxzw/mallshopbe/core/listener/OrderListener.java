package com.jsrdxzw.mallshopbe.core.listener;

import com.jsrdxzw.mallshopbe.bo.OrderListenerBo;
import com.jsrdxzw.mallshopbe.service.CouponBackService;
import com.jsrdxzw.mallshopbe.service.OrderCancelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Slf4j
@Component
public class OrderListener implements MessageListener<OrderListenerBo> {
    @Autowired
    private OrderCancelService orderCancelService;
    @Autowired
    private CouponBackService couponBackService;

    @Override
    public void received(Consumer<OrderListenerBo> consumer, Message<OrderListenerBo> msg) {
        try {
            OrderListenerBo orderListenerBo = msg.getValue();
            orderCancelService.cancelOrder(orderListenerBo);
            couponBackService.returnCoupon(orderListenerBo);
            consumer.acknowledge(msg);
        } catch (Exception e) {
            log.error("get order info error, msg:{}, exception:{}", msg, e);
            consumer.negativeAcknowledge(msg);
        }
    }
}
