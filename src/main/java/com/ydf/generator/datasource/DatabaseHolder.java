package com.ydf.generator.datasource;

import com.ydf.generator.exception.GeneratorException;
import com.ydf.generator.thread.DatabaseContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 数据库Dao实现的管理类
 * @author yuandongfei
 * @date 2019/1/9
 */
@Component
public class DatabaseHolder {

    @Autowired
    private Map<String, DatabaseDao> databaseDao;

    @Autowired
    private DatabaseContextHolder databaseContextHolder;

    public DatabaseDao findDatabaseDao(){
        DatabaseDialect dialect = databaseContextHolder.getDatabaseDialect();
        return findDatabaseDao(dialect);
    }

    public DatabaseDao findDatabaseDao(DatabaseDialect dialect) {
        return findDatabaseDao(dialect.name());
    }

    public DatabaseDao findDatabaseDao(String dialect) {
        String key = String.format("%sDatabaseDao", dialect.toLowerCase());
        DatabaseDao dao = this.databaseDao.get(key);
        if (null == dao) {
            throw new GeneratorException(String.format("%s数据库的DatabaseDao接口实现类不存在", dialect));
        }
        return dao;
    }
}
