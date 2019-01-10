package com.ydf.generator.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/12
 */
@Getter
@Setter
public class ColumnConfig {

    private String columnName;
    private Boolean show;
    private Boolean search;
    private String fieldText;
    private Boolean nullable;
    private Boolean canEdit;

}
