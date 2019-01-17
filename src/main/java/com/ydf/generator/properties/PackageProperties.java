package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2019/1/12
 */
@Setter
@Getter
public class PackageProperties {
    /**
     * 表对应的实体包名
     */
    private String model;
    /**
     * 表对应的dao（Mapper.java）的包名
     *
     */
    private String client;
    /**
     * Service.java 的包名
     */
    private String service;
    /**
     * Controller.java 的包名
     */
    private String controller;
}
