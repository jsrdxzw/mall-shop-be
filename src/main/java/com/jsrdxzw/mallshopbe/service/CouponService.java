package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.core.enumeration.CouponStatus;
import com.jsrdxzw.mallshopbe.exception.NotFoundException;
import com.jsrdxzw.mallshopbe.exception.ParameterException;
import com.jsrdxzw.mallshopbe.model.Activity;
import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.model.UserCoupon;
import com.jsrdxzw.mallshopbe.repository.ActivityRepository;
import com.jsrdxzw.mallshopbe.repository.CouponRepository;
import com.jsrdxzw.mallshopbe.repository.UserCouponRepository;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    public List<Coupon> getByCategoryId(Long cid) {
        return couponRepository.selectCouponsByCategoryId(cid, new Date());
    }

    public List<Coupon> getWholeStoreCoupons() {
        return couponRepository.selectWholeStoreCoupons(true, new Date());
    }

    public List<Coupon> getMyCouponsByStatus(Long uid, Integer status) {
        CouponStatus couponStatus = CouponStatus.toType(status);
        switch (couponStatus) {
            case AVAILABLE:
                return getMyAvailableCoupons(uid);
            case USED:
                return getMyUsedCoupons(uid);
            case EXPIRED:
                return getMyExpiredCoupons(uid);
            default:
                throw new ParameterException(40001);
        }
    }

    public List<Coupon> getMyAvailableCoupons(Long uid) {
        return couponRepository.selectMyAvailableCoupons(uid, new Date());
    }

    private List<Coupon> getMyUsedCoupons(Long uid) {
        return couponRepository.selectMyUsedCoupons(uid, new Date());
    }

    private List<Coupon> getMyExpiredCoupons(Long uid) {
        return couponRepository.selectMyExpiredCoupons(uid, new Date());
    }

    @Transactional(rollbackFor = Exception.class)
    public void collectOneCoupon(Long uid, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new NotFoundException(40003));
        Activity activity = activityRepository.findById(coupon.getActivityId())
                .orElseThrow(() -> new NotFoundException(40010));
        Date now = new Date();
        if (!CommonUtil.isInTime(activity.getStartTime(), now, activity.getEndTime())) {
            throw new ParameterException(40005);
        }
        Optional<UserCoupon> userCoupon = userCouponRepository.findByCouponIdAndUserId(couponId, uid);
        if (userCoupon.isPresent()) {
            throw new ParameterException(40006);
        }
        UserCoupon userCouponNew = new UserCoupon();
        userCouponNew.setCouponId(couponId);
        userCouponNew.setUserId(uid);
        userCouponNew.setStatus(CouponStatus.AVAILABLE);
        userCouponNew.setCreateTime(now);
        userCouponRepository.save(userCouponNew);
    }
}
