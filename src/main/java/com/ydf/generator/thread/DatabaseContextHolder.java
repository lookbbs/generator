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

    public void setDatabaseDialect(DatabaseDialect dialect) {
        session.setAttribute(getKey(), dialect);
        log.info("数据库方言（类型）修改（保存）成功");
    }

    public DatabaseDialect getDatabaseDialect() {
        return (DatabaseDialect) session.getAttribute(getKey());
    }

    private String getKey() {
        return String.format("%s:dialect", session.getId()).toUpperCase();
    }
}
