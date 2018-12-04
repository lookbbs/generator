package com.ydf.generator.mapper;

import com.ydf.generator.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public interface TableMapper {

    /**
     * 根据表名数组查询库下的表结构列表
     *
     * @param schema
     * @param tables 查询条件：表名数组
     * @return
     */
    List<Table> selectList(@Param("schema") String schema, @Param("tables") String[] tables);
}
