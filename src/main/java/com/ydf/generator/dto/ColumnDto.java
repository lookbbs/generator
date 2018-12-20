package com.ydf.generator.dto;

import com.ydf.generator.entity.Column;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class ColumnDto extends Column {
    /**
     * 该列对应java对象的属性名
     */
    private String fieldName;

    /**
     * 该列对应页面上显示的文字
     */
    private String fieldText;
    /**
     * 该列对应的java类型
     */
    private String javaType;
    /**
     * 是否是列表搜索字段
     */
    private Boolean search;
    /**
     * 该列是否在列表页显示
     */
    private Boolean show = true;
    /**
     * 是否编辑该列
     */
    private Boolean canEdit = true;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldText() {
        return fieldText;
    }

    public void setFieldText(String fieldText) {
        this.fieldText = fieldText;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Boolean getSearch() {
        return search;
    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }
}
