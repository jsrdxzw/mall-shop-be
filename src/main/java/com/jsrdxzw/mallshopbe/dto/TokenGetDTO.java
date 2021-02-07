package com.jsrdxzw.mallshopbe.dto;

import com.jsrdxzw.mallshopbe.core.enumeration.LoginType;
import com.jsrdxzw.mallshopbe.core.validator.TokenPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Data
public class TokenGetDTO {
    @NotBlank
    private String account;
    @TokenPassword(max = 30, message = "{token.password}")
    private String password;
    private LoginType type;
}
