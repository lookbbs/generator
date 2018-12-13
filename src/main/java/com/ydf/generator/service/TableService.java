package com.ydf.generator.service;

import com.ydf.generator.dto.TableDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public interface TableService {

    /**
     * 根据表名数组查询库下的表结构列表
     *
     * @param tables 查询条件：表名数组
     * @return
     */
    List<TableDto> selectList(String[] tables);

    /**
     * 保存列配置信息
     * @param table 表名
     * @param data 页面配置后的列数据，json数组字符串
     * @return
     * @throws IOException
     */
    String saveColumn(String table, String data) throws IOException;
}
