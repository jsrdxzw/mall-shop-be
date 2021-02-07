package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.bo.OrderListenerBo;
import com.jsrdxzw.mallshopbe.bo.OrderSkuBo;
import com.jsrdxzw.mallshopbe.exception.ServerErrorException;
import com.jsrdxzw.mallshopbe.model.Order;
import com.jsrdxzw.mallshopbe.repository.OrderRepository;
import com.jsrdxzw.mallshopbe.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Service
public class OrderCancelService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SkuRepository skuRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(OrderListenerBo orderListenerBo) {
        Order order = orderRepository.findById(orderListenerBo.getOid()).orElseThrow(ServerErrorException::new);
        int result = orderRepository.cancelOrder(order.getId());
        if (result != 1) {
            return;
        }
        for (OrderSkuBo snapItem : order.getSnapItems()) {
            skuRepository.recoverStock(snapItem.getId(), snapItem.getCount());
        }
    }
}
