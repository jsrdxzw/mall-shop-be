package com.jsrdxzw.mallshopbe.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuzhiwei
 * @date 2021-02-01
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UnifyResponse {
    private Integer code;
    private String message;
    private String request;
}
