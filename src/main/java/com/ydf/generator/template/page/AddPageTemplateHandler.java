package com.ydf.generator.template.page;

import com.ydf.generator.config.FileProperties;
import com.ydf.generator.template.AbstractTemplateHandler;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class AddPageTemplateHandler extends AbstractTemplateHandler {

    private Boolean enable = true;

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getPage().getAdd();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getPage().getAdd();
    }

    @Override
    public String getName() {
        return "Add.ftl";
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
