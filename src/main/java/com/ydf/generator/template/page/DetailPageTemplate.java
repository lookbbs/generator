package com.ydf.generator.template.page;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.template.AbstractTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class DetailPageTemplate extends AbstractTemplate {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getPage().getDetail();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getPage().getDetail();
    }

    @Override
    public String getName() {
        return "Detail.ftl";
    }

    @Override
    public String getCommon() {
        return "数据详情页面";
    }
}
