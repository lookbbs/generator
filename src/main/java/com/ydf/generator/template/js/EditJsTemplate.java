package com.ydf.generator.template.js;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.template.AbstractTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class EditJsTemplate extends AbstractTemplate {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getJs().getEdit();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getJs().getEdit();
    }

    @Override
    public String getName() {
        return "Edit.js";
    }

    @Override
    public String getCommon() {
        return "数据编辑页的js文件";
    }
}
