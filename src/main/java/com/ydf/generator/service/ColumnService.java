package com.ydf.generator.service;

import com.ydf.generator.dto.ColumnDto;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public interface ColumnService {

    /**
     * 查询指定表的所有列
     *
     * @param table  表名
     * @return
     */
    List<ColumnDto> selectList(String table);

}
