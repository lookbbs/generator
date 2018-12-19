package com.ydf.generator.config;

import com.ydf.generator.service.Cache;
import com.ydf.generator.service.impl.TableMemberCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuandongfei
 * @date 2018/12/12
 */
@Configuration
public class BeanConfig {

    @Bean
    @ConditionalOnMissingBean(Cache.class)
    public Cache tableCache(){
        return new TableMemberCache();
    }
}
