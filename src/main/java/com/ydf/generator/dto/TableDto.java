package com.ydf.generator.dto;

import com.ydf.generator.entity.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Getter
@Setter
public class TableDto extends Table {
    private String basePackage;
    private String entityName;
    private String variableName;
    /**
     * 数据库中表对应的字段列表
     */
    private List<ColumnDto> columns;
}
