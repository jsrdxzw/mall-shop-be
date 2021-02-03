package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Coupon;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2021-02-03
 */
@Data
@NoArgsConstructor
public class CouponPureVO {
    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney;
    private BigDecimal minus;
    private BigDecimal rate;
    private Byte type;
    private String remark;
    private Boolean wholeStore;

    public CouponPureVO(Coupon coupon) {
        BeanUtils.copyProperties(coupon, this);
    }
}
