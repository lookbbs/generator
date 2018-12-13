package com.ydf.generator.service;

import com.ydf.generator.dto.TableDto;

import java.util.List;

/**
 * 表对象的缓存操作接口
 *
 * @author yuandongfei
 * @date 2018/12/12
 */
public interface TableCache {

    /**
     * 存储表结构对象
     *
     * @param table
     */
    void put(TableDto table);

    /**
     * 读取表对象
     *
     * @param tableName
     * @return
     */
    TableDto get(String tableName);

    /**
     * 删除表对象
     *
     * @param tableName
     */
    void remove(String tableName);

    /**
     * 获取所有对象
     * @return
     * @param tables
     */
    List<TableDto> selectList(String[] tables);
}
