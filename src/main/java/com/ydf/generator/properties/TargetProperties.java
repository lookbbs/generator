package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Getter
@Setter
public class TargetProperties {

    /**
     * 类的基础包路径
     */
    private String basePackage;
    /**
     * 生成的文件存储的根目录
     */
    private String baseDir="d:/code";

    /**
     * 根配置，相对于baseDir
     */
    private BaseProperties root;
    /**
     * 页面配置
     */
    private PageProperties page = new PageProperties();
    /**
     * 服务端配置
     */
    private ServerProperties server = new ServerProperties();
    /**
     * 页面js配置
     */
    private JsProperties js = new JsProperties();

}
