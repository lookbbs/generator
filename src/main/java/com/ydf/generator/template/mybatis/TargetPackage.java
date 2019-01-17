package com.ydf.generator.template.mybatis;

import lombok.Getter;
import lombok.Setter;

/**
 * 生成文件的目标package
 * @author yuandongfei
 */
@Setter
@Getter
public class TargetPackage {

    /**
     * 生成的Dao（Mapper）.java 对应的package
     */
    private String client;
    /**
     * 生成的mapper.xml配置文件
     */
    private String map = "mapper";
    /**
     * 表对应的实体
     */
    private String model;
}