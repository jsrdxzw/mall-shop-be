package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    /**
     * findByCouponIdAndUserId
     *
     * @param couponId
     * @param uid
     * @return
     */
    Optional<UserCoupon> findByCouponIdAndUserId(Long couponId, Long uid);
}
