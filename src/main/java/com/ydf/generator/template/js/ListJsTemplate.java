package com.ydf.generator.template.js;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.template.AbstractTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class ListJsTemplate extends AbstractTemplate {

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
    public String getCommon() {
        return "数据列表页面的js文件";
    }
}
