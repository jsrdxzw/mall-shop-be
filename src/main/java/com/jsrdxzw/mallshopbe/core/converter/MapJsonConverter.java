package com.jsrdxzw.mallshopbe.core.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsrdxzw.mallshopbe.exception.ServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;

/**
 * @author xuzhiwei
 * @date 2021-02-04
 */
@Slf4j
@Converter
public class MapJsonConverter implements AttributeConverter<HashMap, String> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(HashMap stringObjectMap) {
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (Exception e) {
            log.error("map to string convert error, map:{}, error:{}", stringObjectMap, e);
            throw new ServerErrorException(9999);
        }
    }

    @Override
    public HashMap convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, HashMap.class);
        } catch (Exception e) {
            log.error("string to map convert error, string:{}, error:{}", s, e);
            throw new ServerErrorException(9999);
        }
    }
}
