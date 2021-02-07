package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.biz.order.OrderPreChecker;
import com.jsrdxzw.mallshopbe.core.LocalUserFactory;
import com.jsrdxzw.mallshopbe.core.interceptors.ScopeLevel;
import com.jsrdxzw.mallshopbe.core.properties.MallOrderProperty;
import com.jsrdxzw.mallshopbe.dto.OrderDTO;
import com.jsrdxzw.mallshopbe.model.Order;
import com.jsrdxzw.mallshopbe.service.OrderService;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import com.jsrdxzw.mallshopbe.vo.OrderCreateVO;
import com.jsrdxzw.mallshopbe.vo.OrderPureVO;
import com.jsrdxzw.mallshopbe.vo.OrderSimplifyVO;
import com.jsrdxzw.mallshopbe.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

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
    @Autowired
    private MallOrderProperty mallOrderProperty;

    @PostMapping
    @ScopeLevel
    public OrderCreateVO createOrder(@Validated @RequestBody OrderDTO orderDTO) {
        Long uid = LocalUserFactory.getUser().getId();
        OrderPreChecker orderPreChecker = orderService.preCheck(uid, orderDTO);
        Long oid = orderService.createOrder(uid, orderDTO, orderPreChecker);
        return OrderCreateVO.builder().id(oid).build();
    }

    @ScopeLevel
    @GetMapping("/detail/{id}")
    public OrderPureVO getOrderDetail(@Positive @PathVariable Long id) {
        Order orderDetail = orderService.getOrderDetail(id);
        return new OrderPureVO(orderDetail, mallOrderProperty.getPayTimeLimit());
    }

    @ScopeLevel
    @GetMapping("/status/unpaid")
    public Paging<OrderSimplifyVO> getUnpaidOrder(@RequestParam(defaultValue = "0") Integer start,
                                                  @RequestParam(defaultValue = "10") Integer count) {
        Pair<Integer, Integer> pair = CommonUtil.convertPageParams(start, count);
        Page<Order> orderPage = orderService.getUnpaidOrder(pair.getFirst(), pair.getSecond());
        Paging<OrderSimplifyVO> paging = Paging.createPaging(orderPage, OrderSimplifyVO::new);
        paging.getItems().forEach(it -> it.setPeriod(mallOrderProperty.getPayTimeLimit()));
        return paging;
    }

    @ScopeLevel
    @GetMapping("/by/status/{status}")
    public Paging<OrderSimplifyVO> getOrderByStatus(@PathVariable int status,
                                                    @RequestParam(defaultValue = "0") Integer start,
                                                    @RequestParam(defaultValue = "10") Integer count) {
        Pair<Integer, Integer> pair = CommonUtil.convertPageParams(start, count);
        Page<Order> orderPage = orderService.getOrderByStatus(status, pair.getFirst(), pair.getSecond());
        Paging<OrderSimplifyVO> paging = Paging.createPaging(orderPage, OrderSimplifyVO::new);
        paging.getItems().forEach(it -> it.setPeriod(mallOrderProperty.getPayTimeLimit()));
        return paging;
    }
}
