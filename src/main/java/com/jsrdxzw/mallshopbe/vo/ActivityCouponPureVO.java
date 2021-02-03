package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Activity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityCouponPureVO extends ActivityPureVO {
    private List<CouponPureVO> coupons;

    public ActivityCouponPureVO(Activity activity) {
        super(activity);
        coupons = activity.getCouponList().stream()
                .map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}
