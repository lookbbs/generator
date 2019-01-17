package com.ydf.generator.cache;

import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.exception.GeneratorException;
import com.ydf.generator.thread.DatabaseContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2019/1/8
 */
@Component
public class DatabaseMemoryCache extends AbstractMemoryCache<DatabaseConfig> {

    @Autowired
    private DatabaseContextHolder databaseContextHolder;

    public DatabaseConfig get() {
        DatabaseDialect dialect = databaseContextHolder.getDatabaseDialect();
        if (null == dialect) {
            throw new GeneratorException("系统未找到主配置信息");
        }
        return super.get(dialect.name());
    }
}
