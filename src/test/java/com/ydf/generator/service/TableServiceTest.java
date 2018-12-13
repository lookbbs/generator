package com.ydf.generator.service;

import com.ydf.generator.dto.TableDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TableServiceTest {

    @Autowired
    private TableService tableService;

    @Test
    public void selectList() {
        String[] tables = "sys_user".split(",");
        List<TableDto> lst = tableService.selectList(tables);
        System.out.println(lst.size());
    }
}