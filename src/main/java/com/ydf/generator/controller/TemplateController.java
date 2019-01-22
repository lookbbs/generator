package com.ydf.generator.controller;

import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/21
 */
@Controller
@RequestMapping("/sys/config")
public class TemplateController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/template")
    public String index() {
        return "code/template";
    }

    @GetMapping("/templates")
    public ResponseEntity<List<BuildFileConfig>> configList() {
        return ResponseEntity.ok(configService.getConfigList());
    }

    @PostMapping("/templates")
    public ResponseEntity<String> config(BuildFileConfig config) {
        configService.saveConfig(config);
        return ResponseEntity.ok("success");
    }
}
