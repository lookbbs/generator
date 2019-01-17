package com.ydf.generator;

import com.ydf.generator.datasource.DatabaseDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yuandongfei
 * @date
 */
@Controller
@RequestMapping
@EnableSwagger2
@SpringBootApplication
public class GeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("dialects", DatabaseDialect.values());
        return "/index";
    }
}
