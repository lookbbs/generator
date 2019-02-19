package com.ydf.generator.datasource;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据库方言（类型）
 *
 * @author yuandongfei
 * @date 2019/1/8
 */
@Getter
public enum DatabaseDialect {

    MySql("com.mysql.jdbc.Driver", "jdbc:mysql://${host}:${port}/${schema}?useUnicode=true&characterEncoding=${encoding}", "information_schema", "`", "`"),
    MySql8("com.mysql.cj.jdbc.Driver", "jdbc:mysql://${host}:${port}/${schema}?useUnicode=true&characterEncoding=${encoding}", "information_schema", "`", "`");

    /**
     * 连接数据库的驱动类名
     */
    private String driverClass;
    /**
     * 连接数据库的字符串
     */
    private String url;
    /**
     * 元数据库（默认的）
     */
    private String metaSchema;
    /**
     * 数据库的用于标记数据库对象名的开始符号，比如ORACLE就是双引号，MYSQL默认是`反引号
     */
    private String beginningDelimiter;
    /**
     * 数据库的用于标记数据库对象名的结束符号，比如ORACLE就是双引号，MYSQL默认是`反引号
     */
    private String endingDelimiter;

    DatabaseDialect(String driverClass, String url, String metaSchema, String beginningDelimiter, String endingDelimiter) {
        this.driverClass = driverClass;
        this.url = url;
        this.metaSchema = metaSchema;
        this.beginningDelimiter = beginningDelimiter;
        this.endingDelimiter = endingDelimiter;
    }

    /**
     * 获取数据库方言配置
     *
     * @param dialect
     * @return
     */
    public static DatabaseDialect getDatabaseDialect(String dialect) {
        DatabaseDialect[] values = values();
        for (DatabaseDialect val : values) {
            if (StringUtils.equalsIgnoreCase(val.name(), dialect)) {
                return val;
            }
        }
        return null;
    }
}
