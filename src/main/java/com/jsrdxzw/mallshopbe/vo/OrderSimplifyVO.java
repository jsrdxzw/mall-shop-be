package com.jsrdxzw.mallshopbe.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Data
public class OrderSimplifyVO {
    private Long id;
    private String orderNo;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;
    private BigDecimal finalTotalPrice;
    private Integer status;
    private Date expiredTime;
    private Date placedTime;
    private Integer period;
}
