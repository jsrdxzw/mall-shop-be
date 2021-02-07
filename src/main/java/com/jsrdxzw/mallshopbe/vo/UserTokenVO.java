package com.jsrdxzw.mallshopbe.vo;

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
public class UserTokenVO {
    private String token;
}
