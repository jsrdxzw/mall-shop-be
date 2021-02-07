package com.jsrdxzw.mallshopbe.vo;

import com.jsrdxzw.mallshopbe.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Data
@NoArgsConstructor
public class OrderPureVO {
    private Integer period;
    private Date createTime;

    public OrderPureVO(Order order, Integer period) {
        BeanUtils.copyProperties(order, this);
        this.period = period;
    }
}
