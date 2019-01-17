package com.ydf.generator.dto;

import com.ydf.generator.entity.Table;
import com.ydf.generator.properties.PackageProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 生成的实体对象映射类
 * @author yuandongfei
 * @date 2019/1/16
 */
@Setter
@Getter
public class GenerateEntity {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 对应的表对象
     */
    private Table data;
    /**
     * 生成时的配置信息
     */
    private Config config;
    /**
     * 基础包的包名
     */
    private String basePackage;

    /**
     * 目标类（文件）存储的目录
     */
    private String targetPath;
    /**
     * 目标实体类的包名(不包含类名)
     */
    private String targetEntityPackage;
    /**
     * 目标类的类名（文件名）
     */
    private String targetEntityClassName;
    /**
     * 目标类对应的变量名(目前根据targetEntityClassName自动生成)
     */
    private String targetEntityVariableName;

    /**
     * 专供模板文件使用，生成模板前，需要将此字段赋值
     */
    private List<ColumnDto> columns;

    /**
     * 专供模板文件使用，生成模板前，需要将此字段赋值
     */
    private PackageProperties packageConfig;
    /**
     * 基础包名转化的路径（将“.”转换为“/”）
     * 专供模板文件使用，生成模板前，需要将此字段赋值
     */
    private String basePackagePath;
}
