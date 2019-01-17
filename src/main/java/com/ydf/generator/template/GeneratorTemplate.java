package com.ydf.generator.template;

/**
 * @author yuandongfei
 * @date 2018/12/19
 */
public interface GeneratorTemplate {

    /**
     * 模板处理者名称
     * @return
     */
    String getName();

    /**
     * 获取模板描述
     * @return
     */
    String getCommon();
}
