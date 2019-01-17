package com.ydf.generator.datasource;

import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.entity.Column;
import com.ydf.generator.entity.Table;
import com.ydf.generator.util.JdbcTypeTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mysql DatabaseDao接口实现
 *
 * @author yuandongfei
 * @date 2019/1/9
 */
@Slf4j
@Component
public class MysqlDatabaseDao implements DatabaseDao {
    @Autowired
    private JdbcDataSource jdbcDataSource;

    @Override
    public Boolean testConnection(DatabaseConfig db) {
        try (Connection connection = jdbcDataSource.getConnection(db)) {
            return null != connection;
        } catch (Exception e) {
            log.error("测试数据库连接发生异常", e);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<String> getDbList(DatabaseConfig db) {
        String sql = "select `schema_name` from `SCHEMATA` ORDER BY SCHEMA_NAME ASC";
        try (
                Connection conn = jdbcDataSource.getConnection(db);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql)
        ) {
            if (null != rs) {
                List<String> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(rs.getString("schema_name"));
                }
                return result;
            }
        } catch (Exception e) {
            log.error("测试数据库连接发生异常", e);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Table> selectTableList(DatabaseConfig db, String[] tables) {
        StringBuilder sql = new StringBuilder("SELECT `table_schema`, `table_name`, `table_comment` " +
                "        FROM `information_schema`.`TABLES` " +
                "        Where `table_type` = 'base table' ");
        if (StringUtils.isNotBlank(db.getSchema())) {
            sql.append(" and `TABLE_SCHEMA` = '").append(db.getSchema()).append("' ");
        }
        if (ArrayUtils.isNotEmpty(tables)) {
            sql.append(" and `table_name` in (");
            for (String t : tables) {
                sql.append("'").append(t).append("'").append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        try (
                Connection conn = jdbcDataSource.getConnection(db);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql.toString())
        ) {
            if (null != rs) {
                List<Table> result = new ArrayList<>();
                while (rs.next()) {
                    Table t = new Table();
                    t.setTableSchema(rs.getString("table_schema"));
                    t.setTableName(rs.getString("table_name"));
                    t.setTableComment(rs.getString("table_comment"));
                    result.add(t);
                }
                return result;
            }
        } catch (Exception e) {
            log.error("获取数据库[{}]表列表发生异常", db.getSchema(), e);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Column> selectColumnList(DatabaseConfig db, String table,boolean onlyPrimaryKey) {
        StringBuilder sql = new StringBuilder("SELECT `column_name`,`data_type`,`column_key`,`is_nullable`,`column_comment` " +
                "FROM `information_schema`.`COLUMNS`");
        sql.append("WHERE `table_schema` = '").append(db.getSchema()).append("'")
                .append(" AND `table_name` = '").append(table).append("'");
        if(onlyPrimaryKey){
            sql.append(" AND COLUMN_KEY = 'PRI' ");
        }
        try (
                Connection conn = jdbcDataSource.getConnection(db);
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql.toString())
        ) {
            if (null != rs) {
                List<Column> result = new ArrayList<>();
                while (rs.next()) {
                    Column c = new Column();
                    c.setColumnName(rs.getString("column_name"));
                    c.setColumnKey(rs.getString("column_key"));
                    c.setJdbcType(rs.getString("data_type"));
                    c.setNullable(rs.getString("is_nullable"));
                    c.setColumnComment(rs.getString("column_comment"));
                    c.setJavaType(JdbcTypeTool.getJavaType(c.getJdbcType()));
                    result.add(c);
                }
                return result;
            }
        } catch (Exception e) {
            log.error("获取数据库[{}]表[{}]的所有列发生异常", db.getSchema(), table, e);
        }
        return Collections.EMPTY_LIST;
    }

}
