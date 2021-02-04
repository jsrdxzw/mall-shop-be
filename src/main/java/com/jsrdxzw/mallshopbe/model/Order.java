package com.jsrdxzw.mallshopbe.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Order extends BaseEntity {
    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;
    private Date expiredTime;
    private Date placedTime;

    private String snapItems;

    private String snapAddress;

    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Integer status;
}
