package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Setter
@Getter
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
}
