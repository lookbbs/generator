package com.ydf.generator.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/19
 */
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return null;
    }
}
