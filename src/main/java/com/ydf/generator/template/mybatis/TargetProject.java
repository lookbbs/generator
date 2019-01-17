package com.ydf.generator.template.mybatis;

import lombok.Getter;
import lombok.Setter;

/**
 * 生成的文件存储目录
 * @author yuandongfei
 * @date 2019/1/11
 */
@Setter
@Getter
public class TargetProject {
    /**
     * 生成的Dao（Mapper）.java 对应的package
     */
    private String client = "src/main/java/";
    /**
     * 生成的mapper.xml配置文件
     */
    private String map = "src/main/resources/";
    /**
     * 表对应的实体
     */
    private String model = "src/main/java/";
}
