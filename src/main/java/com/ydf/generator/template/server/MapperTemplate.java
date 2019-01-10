package com.ydf.generator.template.server;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.template.AbstractTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class MapperTemplate extends AbstractTemplate {

    @Override
    protected FileProperties getTplProperties() {
        return getGeneratorProperties().getTemplate().getServer().getMapper();
    }

    @Override
    protected FileProperties getTargetProperties() {
        return getGeneratorProperties().getTarget().getServer().getMapper();
    }

    @Override
    public String getName() {
        return "extend/Mapper.xml";
    }

    @Override
    public String getCommon() {
        return "数据持久化层SQL文件";
    }
}
