package com.jsrdxzw.mallshopbe.core;

import com.jsrdxzw.mallshopbe.model.User;
import lombok.Data;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Data
public class LocalUser {
    private User user;
    private Integer scope;
}
