package com.ydf.generator.template;

import com.ydf.generator.cache.BuildFileConfigMemoryCache;
import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.dto.TableDto;
import com.ydf.generator.entity.Table;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class TemplateManager {

    @Autowired
    private List<AbstractTemplate> templates;

    @Autowired
    private BuildFileConfigMemoryCache buildFileConfigMemberCache;

    public void exec(List<GenerateEntity> tableConfigs) {
        if (CollectionUtils.isNotEmpty(tableConfigs)) {
            tableConfigs.forEach(entity -> templates.forEach(template -> {
                BuildFileConfig buildFileConfig = buildFileConfigMemberCache.get(template.getName());
                if (null != buildFileConfig && buildFileConfig.getEnable()) {
                    template.process(entity);
                }
            }));
        }
    }
}
