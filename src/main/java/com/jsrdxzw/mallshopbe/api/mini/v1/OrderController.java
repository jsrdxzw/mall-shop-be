package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.biz.OrderChecker;
import com.jsrdxzw.mallshopbe.core.LocalUserFactory;
import com.jsrdxzw.mallshopbe.core.interceptors.ScopeLevel;
import com.jsrdxzw.mallshopbe.dto.OrderDTO;
import com.jsrdxzw.mallshopbe.service.OrderService;
import com.jsrdxzw.mallshopbe.vo.OrderCreateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ScopeLevel
    public OrderCreateVO createOrder(@Validated @RequestBody OrderDTO orderDTO) {
        Long uid = LocalUserFactory.getUser().getId();
        OrderChecker orderChecker = orderService.preCheck(uid, orderDTO);
        return new OrderCreateVO();
    }
}
