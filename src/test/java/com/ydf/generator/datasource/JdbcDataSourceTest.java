package com.ydf.generator.datasource;

import com.ydf.generator.dto.DatabaseConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author yuandongfei
 * @date 2019/1/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcDataSourceTest {

    @Autowired
    private JdbcDataSource jdbcDataSource;

    @Test
    public void getConnection() throws SQLException, ClassNotFoundException {
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

        Connection connection = jdbcDataSource.getConnection(config);
        System.out.println(connection);
    }
}