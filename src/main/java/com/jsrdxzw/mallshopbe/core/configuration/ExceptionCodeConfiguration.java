package com.jsrdxzw.mallshopbe.core.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Configuration
@ConfigurationProperties("mall")
@PropertySource("classpath:exception-code.properties")
@Getter
@Setter
public class ExceptionCodeConfiguration {
    private Map<Integer, String> codes;
}
