package com.jsrdxzw.mallshopbe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsrdxzw.mallshopbe.exception.ServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuzhiwei
 * @date 2021-02-02
 */
@Slf4j
@Component
public class JsonUtil {
    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static <T> String objectToJson(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error("json转string失败, json:{}，exception:{}", t, e);
            throw new ServerErrorException(9999);
        }
    }

    public static <T> T jsonToObject(String s, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(s, typeReference);
        } catch (JsonProcessingException e) {
            log.error("string转json失败, string:{}，exception:{}", s, e);
            throw new ServerErrorException(9999);
        }
    }

    public static <T> T jsonToObject(String s, Class<T> clazz) {
        try {
            return objectMapper.readValue(s, clazz);
        } catch (JsonProcessingException e) {
            log.error("string转json失败, string:{}，exception:{}", s, e);
            throw new ServerErrorException(9999);
        }
    }
}
