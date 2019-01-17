package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Setter
@Getter
public class FileProperties {
    /**
     * 文件存储目录
     */
    private String directory;
    /**
     * 文件名
     */
    private String fileName;

}
