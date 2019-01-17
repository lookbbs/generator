package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2019/1/12
 */
@Setter
@Getter
public class BaseProperties {

    /**
     * 资源目录
     */
    private String resources = "src/main/resources/";
    /**
     * java源码目录
     */
    private String java = "src/main/java/";
}
