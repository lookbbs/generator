package com.ydf.generator.controller;

import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/8
 */
@Controller
@RequestMapping("/sys/config/db")
public class DbController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("dialects", DatabaseDialect.values());
        return "code/database";
    }

    @GetMapping("/dialects")
    @ResponseBody
    public ResponseEntity<DatabaseDialect[]> getAllDialect() {
        return ResponseEntity.ok(DatabaseDialect.values());
    }

    @GetMapping("/{dialect}")
    @ResponseBody
    public ResponseEntity<DatabaseConfig> getConfig(@PathVariable("dialect") String dialect) {
        DatabaseConfig databaseConfig = databaseService.getConfig(dialect);
        return ResponseEntity.ok(databaseConfig);
    }


    @PostMapping("/dbs")
    @ResponseBody
    public ResponseEntity<String> save(DatabaseConfig db) {
        databaseService.saveConfig(db.getDialect(), db);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/dbs")
    @ResponseBody
    public ResponseEntity<List<String>> getDatabases(DatabaseConfig db, HttpServletRequest request) {
        List<String> tables = databaseService.getDatabases(db);
        return ResponseEntity.ok(tables);
    }
}
