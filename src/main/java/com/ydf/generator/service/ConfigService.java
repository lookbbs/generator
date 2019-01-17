package com.ydf.generator.service;

import com.ydf.generator.dto.BuildFileConfig;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/16
 */
public interface ConfigService {
    /**
     * 获取主配置列表
     * @return
     */
    List<BuildFileConfig> getConfigList();

    /**
     * 保存配置
     * @param config
     */
    void saveConfig(BuildFileConfig config);

}
