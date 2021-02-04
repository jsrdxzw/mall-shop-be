package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
