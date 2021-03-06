package com.ydf.generator.template.server;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.template.AbstractTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class ServiceImplTemplate extends AbstractTemplate {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getServer().getServiceImpl();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getServer().getServiceImpl();
    }

    @Override
    public String getName() {
        return "ServiceImpl.java";
    }

    @Override
    public String getCommon() {
        return "服务层接口实现类文件";
    }
}
