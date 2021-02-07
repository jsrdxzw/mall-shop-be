package com.jsrdxzw.mallshopbe.model;

import com.jsrdxzw.mallshopbe.bo.OrderSkuBo;
import com.jsrdxzw.mallshopbe.core.converter.ObjectJsonConverter;
import com.jsrdxzw.mallshopbe.core.enumeration.OrderStatus;
import com.jsrdxzw.mallshopbe.dto.OrderAddressDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @Convert(converter = ObjectJsonConverter.class)
    private List<OrderSkuBo> snapItems;

    @Convert(converter = ObjectJsonConverter.class)
    @Column(columnDefinition = "json")
    private OrderAddressDTO snapAddress;

    private String prepayId;
    private BigDecimal finalTotalPrice;

    private OrderStatus status;
}
