package com.ydf.generator.template;

import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.properties.GeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Slf4j
public abstract class AbstractTemplate implements GeneratorTemplate {
    @Autowired
    private FreemarkerProcessor freemarkerProcessor;

    @Autowired
    private GeneratorProperties generatorProperties;

    /**
     * 获取模板的配置项
     *
     * @return
     */
    protected abstract FileProperties getTplProperties();

    /**
     * 获取生成的目标配置项
     *
     * @return
     */
    protected abstract FileProperties getTargetProperties();

    protected GeneratorProperties getGeneratorProperties() {
        return generatorProperties;
    }

    public void process(GenerateEntity entity) {
        FreemarkerProcessor.TemplateAttribute tmpAttr = freemarkerProcessor.new TemplateAttribute();
        tmpAttr.setName(getTplProperties().getFileName());
        tmpAttr.setPath(getTplProperties().getDirectory());

        FreemarkerProcessor.TargetAttribute tarAttr = freemarkerProcessor.new TargetAttribute();
        tarAttr.setData(entity);
        tarAttr.setName(freemarkerProcessor.writeToString(getTargetProperties().getFileName(), entity));
        String path = freemarkerProcessor.writeToString(getTargetProperties().getDirectory(), entity);
        tarAttr.setPath(String.format("%s%s", generatorProperties.getTarget().getBaseDir(), path));

        try {
            log.info(">>> 处理对象{}读取模板文件：{}，生成目标文件：{}。开始。。。", getClass().getSimpleName(), tmpAttr.getName(), tarAttr.getName());
            File file = freemarkerProcessor.writeToFile(tmpAttr, tarAttr);
            log.info(">>> 处理对象{}读取模板文件：{}，生成目标文件：{}。结束。。。", getClass().getSimpleName(), tmpAttr.getName(), file.getCanonicalPath());
        } catch (IOException e) {
            log.error(">>>处理对象{}读取模板文件：{} --> 目标文件：{} 发生异常", tmpAttr.getName(), tarAttr.getName());
        }
    }
}
