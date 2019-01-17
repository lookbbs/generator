package com.ydf.generator.controller;

import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/16
 */
@RestController
@RequestMapping("/code/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

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
