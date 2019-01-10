package com.ydf.generator.dto;

import com.ydf.generator.entity.Column;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Getter
@Setter
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
    private Boolean search = Boolean.FALSE;
    /**
     * 该列是否在列表页显示
     */
    private Boolean show = Boolean.TRUE;
    /**
     * 是否编辑该列
     */
    private Boolean canEdit = Boolean.TRUE;
}
