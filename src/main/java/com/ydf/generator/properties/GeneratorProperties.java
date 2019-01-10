package com.ydf.generator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Configuration
@ConfigurationProperties(prefix = "ydf")
@EnableConfigurationProperties(GeneratorProperties.class)
public class GeneratorProperties {

    /**
     * 模板配置项
     */
    private TemplateProperties template = new TemplateProperties();

    /**
     * 生成的目标配置项
     */
    private TargetProperties target = new TargetProperties();

    public TemplateProperties getTemplate() {
        return template;
    }

    public void setTemplate(TemplateProperties template) {
        this.template = template;
    }

    public TargetProperties getTarget() {
        return target;
    }

    public void setTarget(TargetProperties target) {
        this.target = target;
    }
}
