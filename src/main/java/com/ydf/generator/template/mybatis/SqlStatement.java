package com.ydf.generator.template.mybatis;

/**
 * 数据库SqlStatement。
 * generatedKey用于生成生成主键的方法，如果设置了该元素，MBG会在生成的<insert>元素中生成一条正确的<selectKey>元素，该元素可选
 */
public enum SqlStatement {
    /**
     * Cloudscape：相当于selectKey的SQL为： VALUESIDENTITY_VAL_LOCAL()
     */
    Cloudscape,
    /**
     * DB2：相当于selectKey的SQL为： VALUESIDENTITY_VAL_LOCAL()
     */
    DB2,
    /**
     * DB2_MF：相当于selectKey的SQL为：SELECT IDENTITY_VAL_LOCAL()FROM SYSIBM.SYSDUMMY1
     */
    DB2_MF,
    /**
     * Derby：相当于selectKey的SQL为：VALUESIDENTITY_VAL_LOCAL()
     */
    Derby,
    /**
     * HSQLDB：相当于selectKey的SQL为：CALL IDENTITY()
     */
    HSQLDB,
    /**
     * Informix：相当于selectKey的SQL为：selectdbinfo('sqlca.sqlerrd1') from systables where tabid=1
     */
    Informix,
    /**
     * MySql：相当于selectKey的SQL为：SELECTLAST_INSERT_ID()
     */
    MySql,
    /**
     * SqlServer：相当于selectKey的SQL为：SELECTSCOPE_IDENTITY()
     */
    SqlServer,
    /**
     * SYBASE：相当于selectKey的SQL为：SELECT @@IDENTITY
     */
    SYBASE,
    /**
     * JDBC：相当于在生成的insert元素上添加useGeneratedKeys="true"和keyProperty属性
     */
    JDBC;

    public static SqlStatement get(String name){
        SqlStatement[] values = values();
        for (SqlStatement s:values){
            if(s.name().equalsIgnoreCase(name)){
                return s;
            }
        }
        return null;
    }
}