package com.ydf.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据表的列对象
 * @author yuandongfei
 * @date 2018/12/3
 */
@Getter
@Setter
public class Column {
    /**
     * 列（字段）名称
     */
    private String columnName;
    /**
     * 字段类型，主键，外键标识
     */
    private String columnKey;
    /**
     * DB的字段数据类型
     */
    private String jdbcType;
    /**
     * java的数据类型
     */
    private String javaType;
    /**
     * 字段是否可为空
     */
    private String nullable;
    /**
     * 字段描述
     */
    private String columnComment;
}
