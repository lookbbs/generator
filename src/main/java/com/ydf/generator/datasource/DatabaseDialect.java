package com.ydf.generator.datasource;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据库方言（类型）
 * @author yuandongfei
 * @date 2019/1/8
 */
@Getter
public enum DatabaseDialect {

    MySql("com.mysql.cj.jdbc.Driver","jdbc:mysql://${host}:${port}/information_schema?useUnicode=true&characterEncoding=${encoding}");

    /**
     * 连接数据库的驱动类名
     */
    private String driverClassName;
    /**
     * 连接数据库的字符串
     */
    private String url;

    DatabaseDialect(String driverClassName, String url) {
        this.driverClassName = driverClassName;
        this.url = url;
    }

    /**
     * 获取数据库方言配置
     * @param dialect
     * @return
     */
    public static DatabaseDialect getDatabaseDialect(String dialect){
        DatabaseDialect[] values = values();
        for (DatabaseDialect val : values){
            if(StringUtils.equalsIgnoreCase(val.name(),dialect)){
                return val;
            }
        }
        return null;
    }
}
