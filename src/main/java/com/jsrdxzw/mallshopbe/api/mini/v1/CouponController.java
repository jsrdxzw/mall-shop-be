package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.service.CouponService;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import com.jsrdxzw.mallshopbe.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/coupon")
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
}
