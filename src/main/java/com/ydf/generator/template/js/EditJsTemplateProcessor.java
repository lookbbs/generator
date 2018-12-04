package com.ydf.generator.template.js;

import com.ydf.generator.config.FileProperties;
import com.ydf.generator.template.AbstractTemplateProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class EditJsTemplateProcessor extends AbstractTemplateProcessor {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getJs().getEdit();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getJs().getEdit();
    }
}