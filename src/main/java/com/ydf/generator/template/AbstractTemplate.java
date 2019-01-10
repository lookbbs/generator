package com.ydf.generator.template;

import com.ydf.generator.properties.FileProperties;
import com.ydf.generator.properties.GeneratorProperties;
import com.ydf.generator.dto.TableDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Slf4j
public abstract class AbstractTemplate implements Template {
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

    /**
     * 目标配置的处理
     *
     * @param data  模型数据对象
     * @param isWeb 是否web操作
     */
    private FileProperties getTargetConfig(TableDto data, boolean isWeb) {
        String fileName = freemarkerProcessor.writeToString(getTargetProperties().getFileName(), data);
        String dir = freemarkerProcessor.writeToString(getTargetProperties().getDirectory(), data);
        if (isWeb) {
            // 如果是web操作，则输出到临时目录，等待操作压缩
            String sysTemp = System.getProperty("java.io.tmpdir");
            getGeneratorProperties().getTarget().setBaseDir(String.format("%s/code/", sysTemp));
        }
        FileProperties target = new FileProperties();
        target.setDirectory(dir);
        target.setFileName(fileName);
        return target;
    }

    /**
     * 处理代码生成流程
     *
     * @param data  模型数据对象
     * @param isWeb 是否web操作
     * @return
     * @throws IOException
     */
    public File process(TableDto data, boolean isWeb) throws IOException {
        FileProperties sourceConfig = getTplProperties();
        FileProperties targetConfig = getTargetConfig(data, isWeb);
        log.info("### 处理对象{}读取模板文件：{}，生成目标文件：{}。开始。。。", getClass().getSimpleName(), sourceConfig.getFileName(), targetConfig.getFileName());
        // 获取模板对象
        freemarker.template.Template template = freemarkerProcessor.getTemplate(getGeneratorProperties().getTemplate().getBasePackagePath(), sourceConfig.getDirectory(), sourceConfig.getFileName());
        // 生成目标文件
        data.setBasePackage(generatorProperties.getTarget().getBasePackage());
        File build = build(data, template, targetConfig);
        log.info("### 处理对象{}读取模板文件：{}，生成目标文件：{}。结束。。。", getClass().getSimpleName(), sourceConfig.getFileName(), build.getCanonicalPath());
        return build;
    }

    private File build(TableDto data, freemarker.template.Template template, FileProperties target) {
        return freemarkerProcessor.writeToFile(getGeneratorProperties().getTarget().getBaseDir(), target.getDirectory(), target.getFileName(), data, template);
    }
}
