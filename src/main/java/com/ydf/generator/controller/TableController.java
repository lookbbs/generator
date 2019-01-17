package com.ydf.generator.controller;

import com.ydf.generator.dto.ColumnDto;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.entity.Table;
import com.ydf.generator.service.ExportService;
import com.ydf.generator.service.TableService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private ExportService exportService;

    @GetMapping
    public String index() {
        return "code/table";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<Table>> list() {
        List<Table> lst = tableService.selectList(null);
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/{table}/config")
    public String pageColumnConfig(@PathVariable("table") String table, Model model) {
        GenerateEntity entity = tableService.getByTableName(table);
        model.addAttribute("record", entity);
        return "code/column";
    }

    @GetMapping("/{table}/columns")
    @ResponseBody
    public ResponseEntity<List<ColumnDto>> getColumnConfig(@PathVariable("table") String table) {
        return ResponseEntity.ok(tableService.getColumns(table));
    }

    @PostMapping("/{table}/columns")
    @ResponseBody
    public ResponseEntity<String> saveColumnConfig(@PathVariable("table") String table, String data) throws IOException {
        return ResponseEntity.ok(tableService.saveColumns(table, data));
    }

    @PostMapping
    public void exportFile(String tables, HttpServletResponse response) throws Exception {
        File export = exportService.export(tables);
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

}
