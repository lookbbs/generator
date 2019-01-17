package com.ydf.generator.template;

import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.entity.Column;
import com.ydf.generator.entity.Table;
import com.ydf.generator.template.mybatis.MybatisGeneratorBuilder;
import com.ydf.generator.template.mybatis.SqlStatement;
import com.ydf.generator.template.mybatis.TargetPackage;
import com.ydf.generator.template.mybatis.TargetProject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.ProgressCallback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisGeneratorProxyTest {

    @Test
    public void generate() {
        TargetPackage targetPackage = new TargetPackage();
        targetPackage.setClient("com.test.dao");
        targetPackage.setModel("com.test.model");
        SqlStatement sqlStatement = SqlStatement.MySql;

        DatabaseConfig config = new DatabaseConfig();
        config.setDialect(DatabaseDialect.MySql.name());
        config.setDriverClass(DatabaseDialect.MySql.getDriverClass());
        config.setHost("172.16.0.235");
        config.setPort("3306");
        config.setUsername("game");
        config.setPassword("gjz1opsdsbJefcDkbC");
        config.setSchema("gm_mini");
        config.setUrl("jdbc:mysql://172.16.0.235:3306/gm_mini?characterEncoding=utf8");
        config.setEncoding("UTF-8");

        String basePath = "d:\\code";

        TargetProject targetProject = new TargetProject();
        targetProject.setClient(Paths.get(basePath, "src/main/java/").toString());
        targetProject.setModel(Paths.get(basePath, "src/main/java/").toString());
        targetProject.setMap(Paths.get(basePath, "src/main/resources/").toString());

        MybatisGeneratorBuilder mg = MybatisGeneratorBuilder.builder()
                .tableConfigs(getTables())
                .databaseConfig(config)
                .sqlStatement(sqlStatement)
                .targetPackage(targetPackage)
                .targetProject(targetProject)
                .build();
        List<String> warnings = mg.generate(null);
        log.info("MyBatis generator 生成代码结束，结果如下：");
        for (String warning : warnings) {
            log.info(warning);
        }
    }

    private List<GenerateEntity> getTables() {
        List<GenerateEntity> lst = new ArrayList<>();
        String tabs = "sys_user";
        String[] split = tabs.split(",");
        for (String t : split) {
            Column key = new Column();
            key.setColumnName("id");
            Table table = new Table();
            table.setTableName(t);
            table.setTableSchema("gm_platform");

            GenerateEntity entity = new GenerateEntity();
            entity.setData(table);
            lst.add(entity);
        }
        return lst;
    }


}