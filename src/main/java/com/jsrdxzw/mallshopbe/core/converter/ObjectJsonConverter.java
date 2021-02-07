package com.jsrdxzw.mallshopbe.core.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jsrdxzw.mallshopbe.util.JsonUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author xuzhiwei
 * @date 2021-02-05
 */
@Converter
public class ObjectJsonConverter<T> implements AttributeConverter<T, String> {
    @Override
    public String convertToDatabaseColumn(T t) {
        return JsonUtil.objectToJson(t);
    }

    @Override
    public T convertToEntityAttribute(String s) {
        return JsonUtil.jsonToObject(s, new TypeReference<>() {});
    }
}
