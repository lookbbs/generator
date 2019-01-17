package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Setter
@Getter
public class PageProperties {
    /**
     * 列表页面
     */
    private FileProperties list = new FileProperties();
    /**
     * 新增页面
     */
    private FileProperties add = new FileProperties();
    /**
     * 编辑页面
     */
    private FileProperties edit = new FileProperties();
    /**
     * 详情页面
     */
    private FileProperties detail = new FileProperties();
}
