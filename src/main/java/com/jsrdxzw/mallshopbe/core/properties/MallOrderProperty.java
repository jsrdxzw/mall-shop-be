package com.jsrdxzw.mallshopbe.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mall.order")
public class MallOrderProperty {
    private Integer payTimeLimit;
    private Integer maxSkuLimit;
}
