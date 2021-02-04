package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.core.LocalUserFactory;
import com.jsrdxzw.mallshopbe.core.UnifyResponse;
import com.jsrdxzw.mallshopbe.core.interceptors.ScopeLevel;
import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.service.CouponService;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import com.jsrdxzw.mallshopbe.vo.CouponCategoryVO;
import com.jsrdxzw.mallshopbe.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/coupon")
@Validated
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/category/{cid}")
    public List<CouponPureVO> getCouponByCategoryId(@PathVariable Long cid) {
        List<Coupon> couponList = couponService.getByCategoryId(cid);
        return CommonUtil.copyBeanList(couponList, CouponPureVO::new);
    }

    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList() {
        List<Coupon> couponList = couponService.getWholeStoreCoupons();
        return CommonUtil.copyBeanList(couponList, CouponPureVO::new);
    }

    @ScopeLevel
    @PostMapping("/collect/{id}")
    public UnifyResponse collectCoupon(@Positive @PathVariable Long id) {
        Long uid = LocalUserFactory.getUser().getId();
        couponService.collectOneCoupon(uid, id);
        return UnifyResponse.createSuccess();
    }

    @ScopeLevel
    @GetMapping("/my/status/{status}")
    public List<CouponPureVO> getMyStatusCoupon(@PathVariable Integer status) {
        Long uid = LocalUserFactory.getUser().getId();
        List<Coupon> couponList = couponService.getMyCouponsByStatus(uid, status);
        return CommonUtil.copyBeanList(couponList, CouponPureVO::new);
    }

    @ScopeLevel
    @GetMapping("/my/available/with_category")
    public List<CouponCategoryVO> getUserCouponWithCategory() {
        Long uid = LocalUserFactory.getUser().getId();
        List<Coupon> availableCoupons = couponService.getMyAvailableCoupons(uid);
        if (CollectionUtils.isEmpty(availableCoupons)) {
            return Collections.emptyList();
        }
        return availableCoupons.stream().map(CouponCategoryVO::new).collect(Collectors.toList());
    }
}
