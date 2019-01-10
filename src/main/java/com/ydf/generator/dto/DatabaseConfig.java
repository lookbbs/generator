package com.ydf.generator.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * 数据库配置对象
 * @date 2019/01/08
 * @author yuandongfei
 */
@Setter
@Getter
public class DatabaseConfig {

    /**
     * 数据库方言（类型）
     */
    private String dialect;
    /**
     * 数据库主机地址
     */
    private String host;

    /**
     * 数据库主机端口
     */
    private String port;
    /**
     * 数据库访问用户名
     */
    private String username;
    /**
     * 数据库访问密码
     */
    private String password;
    /**
     * 数据库schema
     */
    private String schema;
    /**
     * 数据库编码
     */
    private String encoding;
    /**
     * 数据库连接字符串
     */
    private String url;
}