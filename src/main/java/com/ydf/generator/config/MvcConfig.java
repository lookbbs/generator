package com.ydf.generator.config;

import com.ydf.generator.interceptor.ConfigInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author yuandongfei
 * @date 2019/1/9
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private ConfigInterceptor configInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，拦截/code/table开始的路径
        registry.addInterceptor(configInterceptor).addPathPatterns("/code/table/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
