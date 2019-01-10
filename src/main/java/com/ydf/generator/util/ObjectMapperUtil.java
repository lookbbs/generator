package com.ydf.generator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/19
 */
@Slf4j
public class ObjectMapperUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * String 转换成 T 对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertTo(String data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {
            log.error("### JSON字符串：{} 转换 对象：{} 发生异常！", data, clazz, e);
        }
        return null;
    }

    /**
     * String 转换成List 集合对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertToList(String data, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return objectMapper.readValue(data, javaType);
        } catch (IOException e) {
            log.error("### JSON字符串：{} 转换 对象列表：{} 发生异常！", data, clazz, e);
        }
        return null;
    }

    public static String writeValueAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("### 对象：{} 转换  JSON字符串发生异常！", obj, e);
        }
        return null;
    }
}
