package com.ydf.generator.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

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
    private List<Column> primaryKeys;
    private List<Column> columns;

    /**
     * 是否有主键
     * @return Boolean true：有主键
     */
    public Boolean hasPrimaryKey(){
        return CollectionUtils.isNotEmpty(primaryKeys);
    }
}
