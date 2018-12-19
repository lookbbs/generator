package com.ydf.generator.service;

import java.util.List;

/**
 * 表对象的缓存操作接口
 *
 * @author yuandongfei
 * @date 2018/12/12
 */
public interface Cache<T> {

    /**
     * 存储表结构对象
     *
     * @param key
     * @param data
     */
    void put(String key, T data);

    /**
     * 读取表对象
     *
     * @param key
     * @return
     */
    T get(String key);

    /**
     * 删除表对象
     *
     * @param key
     */
    void remove(String key);

    /**
     * 获取所有对象
     *
     * @param keys
     * @return
     */
    List<T> selectList(String[] keys);
}
