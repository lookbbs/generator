package com.ydf.generator.template.server;

import com.ydf.generator.config.FileProperties;
import com.ydf.generator.template.AbstractTemplateProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class ServiceTemplateProcessor extends AbstractTemplateProcessor {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getServer().getService();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getServer().getService();
    }
}