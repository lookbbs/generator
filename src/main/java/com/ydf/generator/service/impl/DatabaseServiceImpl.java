package com.ydf.generator.service.impl;

import com.ydf.generator.cache.DatabaseMemoryCache;
import com.ydf.generator.cache.TableMemoryCache;
import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.datasource.DatabaseHolder;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.service.DatabaseService;
import com.ydf.generator.template.FreemarkerProcessor;
import com.ydf.generator.thread.DatabaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/8
 */
@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseMemoryCache databaseMemberCache;

    @Autowired
    private TableMemoryCache tableMemberCache;

    @Autowired
    private FreemarkerProcessor freemarkerProcessor;

    @Autowired
    private DatabaseHolder databaseHolder;

    @Autowired
    private DatabaseContextHolder databaseContextHolder;

    @Override
    public DatabaseConfig getConfig(String dialect) {
        return databaseMemberCache.get(dialect);
    }

    @Override
    public void saveConfig(String dialect, DatabaseConfig db) {
        DatabaseDialect databaseDialect = DatabaseDialect.getDatabaseDialect(dialect);
        if (null != databaseDialect) {
            formatUrl(databaseDialect.getUrl(), db);
        }
        db.setDriverClass(databaseDialect.getDriverClass());
        databaseContextHolder.setDatabaseDialect(databaseDialect);
        databaseMemberCache.put(dialect, db);
        tableMemberCache.removeAll();
    }

    @Override
    public List<String> getDatabases(DatabaseConfig db) {
        // 此时系统还没有将数据库的配置保存到内存中，所以需要进行url的处理
        DatabaseDialect databaseDialect = DatabaseDialect.getDatabaseDialect(db.getDialect());
        if (null != databaseDialect) {
            if (StringUtils.isBlank(db.getSchema())) {
                db.setSchema(databaseDialect.getMetaSchema());
            }
            formatUrl(databaseDialect.getUrl(), db);
        }
        return databaseHolder.findDatabaseDao(db.getDialect()).getDbList(db);
    }

    private String formatUrl(String urlFormat, DatabaseConfig db) {
        String url = freemarkerProcessor.writeToString(urlFormat, db);
        db.setUrl(url);
        return url;
    }
}
