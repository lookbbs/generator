package com.ydf.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 表对象
 * @author yuandongfei
 * @date 2018/12/3
 */
@Getter
@Setter
public class Table {
    private String tableSchema;
    private String tableName;
    private String tableComment;
    private Column primaryKey;
}
