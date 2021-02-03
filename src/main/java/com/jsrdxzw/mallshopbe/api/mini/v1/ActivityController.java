package com.jsrdxzw.mallshopbe.api.mini.v1;

import com.jsrdxzw.mallshopbe.model.Activity;
import com.jsrdxzw.mallshopbe.service.ActivityService;
import com.jsrdxzw.mallshopbe.vo.ActivityCouponPureVO;
import com.jsrdxzw.mallshopbe.vo.ActivityPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/{name}")
    public ActivityPureVO getActivityByName(@PathVariable String name) {
        Activity activity = activityService.getActivityByName(name);
        return new ActivityPureVO(activity);
    }

    @GetMapping("/{name}/with_coupon")
    public ActivityCouponPureVO getActivityByNameWithCoupons(@PathVariable String name) {
        Activity activity = activityService.getActivityByName(name);
        return new ActivityCouponPureVO(activity);
    }
}
