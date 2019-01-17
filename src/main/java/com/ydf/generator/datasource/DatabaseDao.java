package com.ydf.generator.datasource;

import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.entity.Column;
import com.ydf.generator.entity.Table;

import java.util.List;

/**
 * 数据库操作Dao
 *
 * @author yuandongfei
 * @date 2019/1/9
 */
public interface DatabaseDao {

    /**
     * 测试连接
     *
     * @param db
     * @return
     */
    Boolean testConnection(DatabaseConfig db);

    /**
     * 获取数据库名列表
     *
     * @param db
     * @return
     */
    List<String> getDbList(DatabaseConfig db);

    /**
     * 根据表名数组查询库下的表结构列表
     *
     * @param db
     * @param tables 查询条件：表名数组
     * @return
     */
    List<Table> selectTableList(DatabaseConfig db, String[] tables);

    /**
     * 查询指定表的所有列
     *
     * @param db
     * @param table 表名
     * @param onlyPk 是否只获取主键，true：只返回主键，false：全部（包含主键）
     * @return
     */
    List<Column> selectColumnList(DatabaseConfig db, String table,boolean onlyPk);
}
