package com.jsrdxzw.mallshopbe.biz.coupon;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
@Component
public class CouponDiscount implements ICouponDiscount {
    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount) {
        return original.multiply(discount).setScale(2, RoundingMode.HALF_EVEN);
    }
}
