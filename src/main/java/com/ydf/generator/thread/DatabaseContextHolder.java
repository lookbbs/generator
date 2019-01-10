package com.ydf.generator.thread;

import com.ydf.generator.datasource.DatabaseDialect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * 保存一个线程安全的DatabaseDialect容器
 *
 * @author yuandongfei
 * @date 2019/1/8
 */
@Slf4j
@Component
public class DatabaseContextHolder {

    @Autowired
    private HttpSession session;

    private static final String DIALECT_SESSION_KEY = "dialect";

    public void setDatabaseDialect(DatabaseDialect dialect) {
        session.setAttribute(DIALECT_SESSION_KEY, dialect);
        log.info("保存成功");
    }

    public DatabaseDialect getDatabaseDialect() {
        return (DatabaseDialect) session.getAttribute(DIALECT_SESSION_KEY);
    }
}
