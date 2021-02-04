package com.jsrdxzw.mallshopbe.dto;

import lombok.Data;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Data
public class OrderAddressDTO {
    private String userName;
    private String province;
    private String city;
    private String county;
    private String mobile;
    private String nationalCode;
    private String postalCode;
    private String detail;
}
