package com.ydf.generator.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 子项（表中的各列）的配置
 *
 * @author yuandongfei
 * @date 2019/1/16
 */
@Getter
@Setter
public class ConfigItem {
    /**
     * 是否显示该项
     */
    private Boolean show = Boolean.TRUE;
    /**
     * 是否是列表页的搜索条件
     */
    private Boolean search = Boolean.FALSE;
    /**
     * 是否可为空
     */
    private Boolean nullable = Boolean.FALSE;
    /**
     * 是否编辑项
     */
    private Boolean canEdit = Boolean.TRUE;
    /**
     * 对应的列名称
     */
    private String columnName;
    /**
     * 列名对应的字段名称
     */
    private String fieldName;
    /**
     * 字段的描述文本（通常是列表的表头，编辑页的各列的标题）
     */
    private String fieldText;
}
