package com.ydf.generator.dto;

import com.ydf.generator.entity.Column;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class ColumnDto extends Column {
    private String fieldName;
    private String javaType;
    private boolean isSearch;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }
}
