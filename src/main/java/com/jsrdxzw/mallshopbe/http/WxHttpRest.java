package com.jsrdxzw.mallshopbe.http;

import com.jsrdxzw.mallshopbe.util.JsonUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Setter
@Component
@ConfigurationProperties(prefix = "wx")
public class WxHttpRest {
    private String code2SessionUrl;
    private String appid;
    private String appsecret;

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    public Map<String, Object> code2Session(String code) {
        String url = MessageFormat.format(this.code2SessionUrl, this.appid, this.appsecret, code);
        String object = restTemplate.getForObject(url, String.class);
        return JsonUtil.jsonToObject(object, Map.class);
    }
}
