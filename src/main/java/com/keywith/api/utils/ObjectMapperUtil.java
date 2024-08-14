package com.keywith.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> Map<String, Object> convertEntityToMap(T entity) {
        return objectMapper.convertValue(entity, Map.class);
    }

    public static <T> T mapToEntity(Map<String, Object> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
