package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Coupon;
import com.jsrdxzw.mallshopbe.util.CommonUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CouponCategoryVO extends CouponPureVO {
    private List<CategoryPureVO> categories;

    public CouponCategoryVO(Coupon coupon) {
        super(coupon);
        this.categories = CommonUtil.copyBeanList(coupon.getCategoryList(), CategoryPureVO::new);
    }
}
