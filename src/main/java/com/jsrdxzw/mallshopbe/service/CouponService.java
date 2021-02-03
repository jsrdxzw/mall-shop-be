package com.jsrdxzw.mallshopbe.service;

import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> getByCategoryId(Long cid) {
        return couponRepository.selectCouponsByCategoryId(cid, new Date());
    }

    public List<Coupon> getWholeStoreCoupons() {
        return couponRepository.selectWholeStoreCoupons(true, new Date());
    }
}
