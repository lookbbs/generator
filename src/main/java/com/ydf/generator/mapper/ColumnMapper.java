package com.ydf.generator.mapper;

import com.ydf.generator.entity.Column;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public interface ColumnMapper {

    /**
     * 查询指定表的所有列
     *
     * @param table 表名
     * @return
     */
    List<Column> selectList(String table);
}
