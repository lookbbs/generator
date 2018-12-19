package com.ydf.generator.controller;

import com.ydf.generator.dto.ColumnDto;
import com.ydf.generator.dto.TableDto;
import com.ydf.generator.service.ColumnService;
import com.ydf.generator.service.ExportService;
import com.ydf.generator.service.TableService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/5
 */
@Controller
@RequestMapping("/code/table")
public class TableController {

    @Autowired
    private TableService tableService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private ExportService exportService;

    @GetMapping
    public String index(Model model) {
        List<TableDto> lst = tableService.selectList(null);
        if (!CollectionUtils.isEmpty(lst)) {
            model.addAttribute("list", lst);
            model.addAttribute("configList", tableService.getConfigList());
            model.addAttribute("schema", lst.get(0).getTableSchema());
        }
        return "code/table";
    }

    @GetMapping("/{table}/column")
    public String showColumn(@PathVariable("table") String table, Model model) {
        List<ColumnDto> lst = columnService.selectList(table);
        if (!CollectionUtils.isEmpty(lst)) {
            model.addAttribute("list", lst);
            model.addAttribute("table", table);
        }
        return "code/column";
    }

    @PostMapping("/{table}/column")
    @ResponseBody
    public ResponseEntity<String> saveColumnConfig(@PathVariable("table") String table, String data) throws IOException {
        return ResponseEntity.ok(tableService.saveColumn(table, data));
    }

    @PostMapping
    public void exportFile(String tables, HttpServletResponse response) throws Exception {
        File export = exportService.export(tables, true);
        if (null != export) {
            try (
                    InputStream inputStream = new FileInputStream(export);
                    OutputStream outputStream = response.getOutputStream()
            ) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + export.getName());
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            }
            export.delete();
        }
    }

    @PostMapping("/config")
    public ResponseEntity<String> config(String data) {
        tableService.saveConfig(data);
        return ResponseEntity.ok("success");
    }
}
