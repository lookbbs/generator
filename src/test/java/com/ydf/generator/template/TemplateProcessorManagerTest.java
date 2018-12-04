package com.ydf.generator.template;

import com.ydf.generator.config.ThreadManager;
import com.ydf.generator.dto.TableDto;
import com.ydf.generator.entity.Table;
import com.ydf.generator.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateProcessorManagerTest {

    @Autowired
    private TableService tableService;

    @Autowired
    private TemplateProcessorManager templateProcessorManager;

    @Test
    public void exec() {
        String schema = "gm_platform";
        String[] tables = "sys_user".split(",");
        List<TableDto> lst = tableService.selectList(schema, tables);
        for (TableDto d : lst) {
            templateProcessorManager.exec(d);
        }
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}