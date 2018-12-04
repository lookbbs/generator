package com.ydf.generator.entity;

/**
 * 表对象
 * @author yuandongfei
 * @date 2018/12/3
 */
public class Table {
    private String tableName;
    private String tableComment;
    private Column primaryKey;
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Column getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
    }
}
