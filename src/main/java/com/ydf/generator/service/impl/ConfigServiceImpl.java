package com.ydf.generator.service.impl;

import com.ydf.generator.cache.BuildFileConfigMemoryCache;
import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/16
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private BuildFileConfigMemoryCache buildFileConfigMemberCache;


    @Override
    public List<BuildFileConfig> getConfigList() {
        return buildFileConfigMemberCache.selectList(null);
    }

    @Override
    public void saveConfig(BuildFileConfig config) {
        BuildFileConfig c = buildFileConfigMemberCache.get(config.getName());
        c.setEnable(config.getEnable());
        buildFileConfigMemberCache.put(config.getName(), c);
    }
}
