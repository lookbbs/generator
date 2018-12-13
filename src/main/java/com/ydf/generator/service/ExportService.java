package com.ydf.generator.service;

import java.io.File;

/**
 * @author yuandongfei
 * @date 2018/12/13
 */
public interface ExportService {

    /**
     * 导出代码文件
     * 如果是web操作，代码文件生成ZIP打包文件下载，如果是mian函数则生成到配置的路径
     * @param tables 需要生成代码的表名，多个表名用“,”分割
     * @param isWeb 是否web操作。true：是，false: 否
     */
    File export(String tables, boolean isWeb) throws Exception;
}
