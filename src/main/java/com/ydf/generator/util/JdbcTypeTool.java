package com.ydf.generator.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class JdbcTypeTool {
    private static String[] jdbcTypeArray = {"BIT", "TINYINT", "SMALLINT", "INT", "INTEGER", "BIGINT", "NUMERIC", "DECIMAL", "CHAR", "VARCHAR", "DATE", "TIME", "TIMESTAMP", "DATETIME"};
    private static String[] javaTypeArray = {"Integer", "Integer", "Integer", "Integer", "Integer", "Long", "Long", "Long", "String", "String", "Date", "Date", "Date", "Date"};

    public static String getJavaType(String jdbcType) {
        if (StringUtils.isBlank(jdbcType)) {
            return StringUtils.EMPTY;
        }
        for (int i = 0; i < jdbcTypeArray.length; i++) {
            if (StringUtils.equalsIgnoreCase(jdbcType, jdbcTypeArray[i])) {
                return javaTypeArray[i];
            }
        }
        return StringUtils.EMPTY;
    }
}
