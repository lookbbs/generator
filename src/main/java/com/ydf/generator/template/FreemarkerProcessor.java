package com.ydf.generator.template;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class FreemarkerProcessor {

    public Template getTemplate(String basePackagePath, String directory, String name) throws IOException {
        return getTemplate(basePackagePath, String.format("%s/%s", directory, name));
    }

    public Template getTemplate(String basePackagePath, String templatePath) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        File file = new File(this.getClass().getResource("/").getPath()).getParentFile();
        cfg.setDirectoryForTemplateLoading(new File(file,basePackagePath));
        cfg.setDefaultEncoding("UTF-8");
        Template template = cfg.getTemplate(templatePath);
        template.setOutputEncoding("UTF-8");
        return template;
    }

    /**
     * 根据模板文件，模型数据生成对应的文件
     * @param baseDir 存储生成文件的根路径
     * @param targetDir 存储生成文件的路径
     * @param outFile 输出的文件名
     * @param data 模型数据对象
     * @param template 模板引擎
     * @return 生成的文件对象
     */
    public File writeToFile(String baseDir, String targetDir, String outFile, Object data, Template template) {
        File targetDirPath = mkdirs(String.format("%s%s",baseDir , targetDir));
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
