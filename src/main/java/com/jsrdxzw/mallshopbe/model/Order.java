package com.jsrdxzw.mallshopbe.model;

import com.jsrdxzw.mallshopbe.core.enumeration.OrderStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Order extends BaseEntity {
    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer totalCount;
    private String snapImg;
    private String snapTitle;
    private Date expiredTime;
    private Date placedTime;

    @Column(columnDefinition = "json")
    private String snapItems;

    @Column(columnDefinition = "json")
    private String snapAddress;

    private String prepayId;
    private BigDecimal finalTotalPrice;
    private OrderStatus status;
}
