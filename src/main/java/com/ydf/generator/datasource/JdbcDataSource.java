package com.ydf.generator.datasource;

import com.ydf.generator.dto.DatabaseConfig;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author yuandongfei
 * @date 2019/1/9
 */
@Component
public class JdbcDataSource {

    public Connection getConnection(DatabaseConfig config) throws ClassNotFoundException, SQLException {
        DatabaseDialect databaseDialect = DatabaseDialect.getDatabaseDialect(config.getDialect());

        Class.forName(databaseDialect.getDriverClassName());
        return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
    }
}
