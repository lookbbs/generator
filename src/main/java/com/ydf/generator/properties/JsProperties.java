package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Setter
@Getter
public class JsProperties {
    /**
     * 列表页的js
     */
    private FileProperties list = new FileProperties();

    /**
     * 编辑页的js
     */
    private FileProperties edit = new FileProperties();
}
