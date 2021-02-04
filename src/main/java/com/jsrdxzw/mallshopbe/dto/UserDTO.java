package com.jsrdxzw.mallshopbe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Data
public class UserDTO {
    @NotBlank
    private String nickname;
}
