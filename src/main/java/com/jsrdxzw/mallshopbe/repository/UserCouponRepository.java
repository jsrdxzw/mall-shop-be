package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.core.enumeration.CouponStatus;
import com.jsrdxzw.mallshopbe.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * findByCouponIdAndUserIdAndStatus
     *
     * @param couponId
     * @param uid
     * @param status
     * @return
     */
    Optional<UserCoupon> findByCouponIdAndUserIdAndStatus(Long couponId, Long uid, CouponStatus status);

    /**
     * 核销优惠券
     *
     * @param couponId
     * @param oid
     * @param uid
     * @return
     */
    @Modifying
    @Query("update UserCoupon uc\n" +
            "set uc.status = 2, uc.orderId = :oid\n" +
            "where uc.userId = :uid\n" +
            "and uc.couponId = :couponId\n" +
            "and uc.status = 1\n" +
            "and uc.orderId is null\n")
    int writeOffCoupon(Long couponId, Long oid, Long uid);

    /**
     * 归还优惠券
     *
     * @param couponId
     * @param uid
     */
    @Modifying
    @Query("update UserCoupon uc set uc.status = 1, uc.orderId = null\n" +
            "where uc.couponId = :couponId\n" +
            "and uc.userId = :uid\n" +
            "and uc.orderId is not null\n" +
            "and uc.status = 2")
    void returnBack(Long couponId, Long uid);
}
