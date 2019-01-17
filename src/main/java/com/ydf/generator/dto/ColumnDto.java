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
     * 列的配置
     */
    private ConfigItem config;
}
