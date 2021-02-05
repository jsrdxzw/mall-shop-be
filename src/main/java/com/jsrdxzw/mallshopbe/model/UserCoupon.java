package com.jsrdxzw.mallshopbe.model;

import com.jsrdxzw.mallshopbe.core.enumeration.CouponStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Where(clause = "delete_time is null")
public class UserCoupon extends BaseEntity {
    private Long userId;
    private Long couponId;
    private Long orderId;
    private CouponStatus status;
}
