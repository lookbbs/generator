package com.ydf.generator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydf.generator.dto.ColumnDto;
import com.ydf.generator.entity.Column;
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
public class ColumnServiceTest {

    @Autowired
    private ColumnService columnService;

    @Test
    public void selectList() throws JsonProcessingException {
        String schema = "gm_platform";
        String table = "sys_user";
        List<ColumnDto> lst = columnService.selectList(schema, table);
        for (Column col : lst) {
            System.out.println(new ObjectMapper().writeValueAsString(col));
        }
    }
}