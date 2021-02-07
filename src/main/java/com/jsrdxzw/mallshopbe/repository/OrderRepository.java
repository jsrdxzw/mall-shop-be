package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * 未支付订单超时取消订单
     *
     * @param oid
     * @return
     */
    @Modifying
    @Query("update Order o set o.status = 5\n" +
            "where o.status = 1 and o.id = :oid")
    int cancelOrder(Long oid);
}
