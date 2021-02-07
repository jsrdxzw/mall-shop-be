package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.core.enumeration.OrderStatus;
import com.jsrdxzw.mallshopbe.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

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

    /**
     * 找到指定状态的订单
     *
     * @param now
     * @param status
     * @param uid
     * @param pageable
     * @return
     */
    Page<Order> findByExpiredTimeGreaterThanAndStatusAndUserIdOrderByCreateTimeDesc(
            Date now, OrderStatus status, Long uid, Pageable pageable);

    /**
     * findByUserIdOrderByCreateTimeDesc
     *
     * @param userId
     * @param pageable
     * @return
     */
    Page<Order> findByUserIdOrderByCreateTimeDesc(Long userId, Pageable pageable);

    /**
     * findByUserIdAndStatus
     *
     * @param userId
     * @param status
     * @param pageable
     * @return
     */
    Page<Order> findByUserIdAndStatus(Long userId, OrderStatus status, Pageable pageable);
}
