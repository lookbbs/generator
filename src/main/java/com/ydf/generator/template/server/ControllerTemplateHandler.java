package com.ydf.generator.template.server;

import com.ydf.generator.config.FileProperties;
import com.ydf.generator.template.AbstractTemplateHandler;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class ControllerTemplateHandler extends AbstractTemplateHandler {

    private Boolean enable = true;

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getServer().getController();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getServer().getController();
    }

    @Override
    public String getName() {
        return "Controller.java";
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
