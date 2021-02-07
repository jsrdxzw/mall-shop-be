package com.jsrdxzw.mallshopbe.biz.coupon;

import java.math.BigDecimal;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
public interface ICouponDiscount {
    /**
     * 打折之后价格
     *
     * @param original
     * @param discount
     * @return
     */
    BigDecimal discount(BigDecimal original, BigDecimal discount);
}
