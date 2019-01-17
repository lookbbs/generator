package com.ydf.generator.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 生成时的配置信息
 *
 * @author yuandongfei
 * @date 2019/1/16
 */
@Setter
@Getter
public class Config {
    /**
     * 配置名称
     * 映射一个模板
     */
    private String name;
    /**
     * 是否生成
     * true : 生成，false：不生成
     */
    private Boolean enable = Boolean.TRUE;
    /**
     * 映射的模板信息描述
     */
    private String comment;

    /**
     * 子项配置列表
     */
    private List<ConfigItem> configItems;
}
