package com.ydf.generator.template.js;

import com.ydf.generator.config.FileProperties;
import com.ydf.generator.template.AbstractTemplateHandler;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class ListJsTemplateHandler extends AbstractTemplateHandler {

    private Boolean enable = true;

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getJs().getList();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getJs().getList();
    }

    @Override
    public String getName() {
        return "List.js";
    }

    @Override
    public Boolean isEnable() {
        return this.enable;
    }

    @Override
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
