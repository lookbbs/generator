package com.ydf.generator.service;

import com.ydf.generator.dto.ColumnDto;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.entity.Table;

import java.io.IOException;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public interface TableService {
    /**
     * 获取GenerateEntity对象
     * @param tableName
     * @return
     */
    GenerateEntity getByTableName(String tableName);

    /**
     * 根据表名数组查询库下的表结构列表
     *
     * @param tables 查询条件：表名数组
     * @return
     */
    List<Table> selectList(String[] tables);

    /**
     * 保存列配置信息
     * @param table 表名
     * @param data 页面配置后的列数据，json数组字符串
     * @return
     * @throws IOException
     */
    String saveColumns(String table, String data) throws IOException;

    /**
     * 获取
     * @param table
     * @return
     */
    List<ColumnDto> getColumns(String table);
}
