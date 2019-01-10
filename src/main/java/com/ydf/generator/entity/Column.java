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
    private String columnName;
    private String columnKey;
    private String jdbcType;
    private String nullable;
    private String columnComment;
}
