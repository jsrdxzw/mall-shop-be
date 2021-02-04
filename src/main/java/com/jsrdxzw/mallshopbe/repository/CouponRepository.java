package com.jsrdxzw.mallshopbe.repository;

import com.jsrdxzw.mallshopbe.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
public interface CouponRepository extends JpaRepository<Coupon, Long>, JpaSpecificationExecutor<Coupon> {

    /**
     * selectCouponsByCategoryId
     *
     * @param cid
     * @param now
     * @return
     */
    @Query("select c from Coupon c\n" +
            "join c.categoryList ca\n" +
            "join Activity a on a.id = c.activityId\n" +
            "where ca.id = :cid\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now")
    List<Coupon> selectCouponsByCategoryId(Long cid, Date now);

    /**
     * selectWholeStoreCoupons
     *
     * @param now
     * @param isWholeStore
     * @return
     */
    @Query("select c from Coupon c\n" +
            "join Activity a on c.activityId = a.id\n" +
            "where c.wholeStore = :isWholeStore\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now")
    List<Coupon> selectWholeStoreCoupons(Boolean isWholeStore, Date now);

    /**
     * 查询我的可用优惠券
     *
     * @param uid
     * @param now
     * @return
     */
    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id = uc.couponId\n" +
            "join User u\n" +
            "on u.id = uc.userId\n" +
            "where uc.status = 1\n" +
            "and u.id = :uid\n" +
            "and c.startTime < :now\n" +
            "and c.endTime > :now\n" +
            "and uc.orderId is null")
    List<Coupon> selectMyAvailableCoupons(Long uid, Date now);

    /**
     * 查询我已经使用的优惠券
     *
     * @param uid
     * @param now
     * @return
     */
    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id = uc.couponId\n" +
            "join User u\n" +
            "on u.id = uc.userId\n" +
            "where uc.status = 2\n" +
            "and u.id = :uid\n" +
            "and c.startTime < :now\n" +
            "and c.endTime > :now\n" +
            "and uc.orderId is not null")
    List<Coupon> selectMyUsedCoupons(Long uid, Date now);

    /**
     * 查询我已经过期的优惠券
     *
     * @param uid
     * @param now
     * @return
     */
    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id = uc.couponId\n" +
            "join User u\n" +
            "on u.id = uc.userId\n" +
            "where uc.status <> 2\n" +
            "and u.id = :uid\n" +
            "and c.endTime < :now\n" +
            "and uc.orderId is null")
    List<Coupon> selectMyExpiredCoupons(Long uid, Date now);
}
