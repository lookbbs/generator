package com.ydf.generator.dto;

import com.ydf.generator.entity.Table;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class TableDto extends Table {
    private String basePackage;
    private String entityName;
    private String variableName;
    /**
     * 数据库中表对应的字段列表
     */
    private List<ColumnDto> columns;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public List<ColumnDto> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDto> columns) {
        this.columns = columns;
    }
}
