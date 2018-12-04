package com.ydf.generator.template.page;

import com.ydf.generator.config.FileProperties;
import com.ydf.generator.template.AbstractTemplateProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class AddPageTemplateProcessor extends AbstractTemplateProcessor {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getPage().getAdd();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getPage().getAdd();
    }
}
