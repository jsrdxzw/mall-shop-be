package com.jsrdxzw.mallshopbe.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListenerBo {
    private Long oid;
    private Long couponId;
    private Long uid;
}
