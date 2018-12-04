package com.ydf.generator.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydf.generator.config.FileProperties;
import com.ydf.generator.config.GeneratorProperties;
import com.ydf.generator.dto.TableDto;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;

import java.io.IOException;
import java.util.Map;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public abstract class AbstractTemplateProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
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

    public void process(TableDto data) throws IOException {
        FileProperties list = getTplProperties();
        String fileName = freemarkerProcessor.writeToString(getTargetProperties().getFileName(), data);
        getTargetProperties().setFileName(fileName);
        logger.info("### 处理对象{}读取模板文件：{}，生成目标文件：{}。开始。。。", getClass().getSimpleName(), list.getFileName(), getTargetProperties().getFileName());
        // 获取模板对象
        Template template = freemarkerProcessor.getTemplate(getGeneratorProperties().getTemplate().getBasePackagePath(), list.getDirectory(), list.getFileName());
        // 生成目标文件
        Map<String, Object> map = BeanMap.create(data);
        map.put("basePackage", getGeneratorProperties().getTarget().getBasePackage());
        build(data, template);
        logger.info("### 处理对象{}读取模板文件：{}，生成目标文件：{}。结束。。。", getClass().getSimpleName(), list.getFileName(), getTargetProperties().getFileName());
    }

    private void build(Object data, Template template) {
        FileProperties list = getTargetProperties();
        freemarkerProcessor.writeToFile(getGeneratorProperties().getTarget().getBaseDir(), list.getDirectory(), list.getFileName(), data, template);
    }
}
