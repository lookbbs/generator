package com.ydf.generator.template;

import com.ydf.generator.util.ObjectMapperUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Slf4j
@Component
public class FreemarkerProcessor {
    @Setter
    @Getter
    abstract class Attribute {
        /**
         * 模板文件/目标文件 存放的路径
         */
        private String path;
        /**
         * 模板文件/目标文件 名称
         */
        private String name;
    }

    /**
     * 模板文件属性
     */
    class TemplateAttribute extends Attribute {
    }

    /**
     * 模板文件属性
     */
    @Setter
    @Getter
    class TargetAttribute extends Attribute {
        /**
         * 生成目标文件需要的数据对象
         */
        private Object data;
    }

    private Template getTemplate(TemplateAttribute attribute) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        File file = new File(this.getClass().getResource("/").getPath()).getParentFile();
        cfg.setDirectoryForTemplateLoading(new File(file, attribute.getPath()));
        cfg.setDefaultEncoding("UTF-8");
        Template template = cfg.getTemplate(attribute.getName());
        template.setOutputEncoding("UTF-8");
        return template;
    }

    public File writeToFile(TemplateAttribute ta, TargetAttribute target) {
        log.info(">>> 生成文件的data：{}", ObjectMapperUtil.writeValueAsString(target.getData()));
        File targetDirPath = mkdirs(target.getPath());
        File targetFile = new File(targetDirPath, target.getName());
        try (FileWriter out = new FileWriter(targetFile)) {
            Template template = getTemplate(ta);
            template.process(target.getData(), out);
            return targetFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据模板文件，模型数据生成对应的文件
     *
     * @param baseDir   存储生成文件的根路径
     * @param targetDir 存储生成文件的路径
     * @param outFile   输出的文件名
     * @param data      模型数据对象
     * @param template  模板引擎
     * @return 生成的文件对象
     */
    public File writeToFile(String baseDir, String targetDir, String outFile, Object data, Template template) {
        log.info(">>> 生成文件的data：{}", ObjectMapperUtil.writeValueAsString(data));
        File targetDirPath = mkdirs(String.format("%s%s", baseDir, targetDir));
        File target = new File(targetDirPath, outFile);
        try (FileWriter out = new FileWriter(target)) {
            template.process(data, out);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String writeToString(String templateContent, Object data) {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("template", templateContent);
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setTemplateLoader(stringLoader);

        try {
            Template template = cfg.getTemplate("template", "UTF-8");
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return templateContent;
    }

    private File mkdirs(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
