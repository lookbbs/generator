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

    public void writeToFile(String baseDir,String targetDir, String outFile, Object data, Template template) {
        File targetDirPath = mkdirs(String.format("%s%s",baseDir , targetDir));
        try (FileWriter out = new FileWriter(new File(targetDirPath, outFile))) {
            template.process(data, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
