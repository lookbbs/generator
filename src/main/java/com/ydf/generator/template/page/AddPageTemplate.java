package com.ydf.generator.template.page;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.template.AbstractTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class AddPageTemplate extends AbstractTemplate {

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
    public String getCommon() {
        return "数据添加页面";
    }
}
