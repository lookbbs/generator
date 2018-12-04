package com.ydf.generator.service;

import com.ydf.generator.dto.TableDto;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public interface TableService {

    /**
     * 根据表名数组查询库下的表结构列表
     *
     * @param schema
     * @param tables 查询条件：表名数组
     * @return
     */
    List<TableDto> selectList(String schema, String[] tables);
}
