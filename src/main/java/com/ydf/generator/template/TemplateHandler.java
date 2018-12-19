package com.ydf.generator.template;

/**
 * @author yuandongfei
 * @date 2018/12/19
 */
public interface TemplateHandler {

    /**
     * 模板处理者名称
     * @return
     */
    String getName();

    /**
     * 是否启用该模板处理器
     * @return
     */
    Boolean isEnable();

    /**
     * 设置启用标志
     */
    void setEnable(Boolean enable);
}
