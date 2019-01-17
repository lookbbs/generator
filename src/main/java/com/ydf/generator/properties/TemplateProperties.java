package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Setter
@Getter
public class TemplateProperties {

    /**
     * 模板存储的根目录
     */
    private String basePath;
    /**
     * 页面模板配置
     */
    private PageProperties page = new PageProperties();

    private JsProperties js = new JsProperties();
    /**
     * 服务端的模板文件配置
     */
    private ServerProperties server = new ServerProperties();
}
