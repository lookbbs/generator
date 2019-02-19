package com.ydf.generator.template;

import com.ydf.generator.cache.BuildFileConfigMemoryCache;
import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.dto.TableDto;
import com.ydf.generator.entity.Table;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
                    try {
                        template.process(entity);
                    } catch (Throwable e) {
                        log.error(">>> 模板处理程序：{}，处理表：{}时发生异常", template.getClass().getSimpleName(), entity.getTableName(), e);
                    }
                }
            }));
        }
    }
}
