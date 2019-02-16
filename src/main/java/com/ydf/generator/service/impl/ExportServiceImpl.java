package com.ydf.generator.service.impl;

import com.ydf.generator.cache.DatabaseMemoryCache;
import com.ydf.generator.cache.GenerateMemoryCache;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.exception.GeneratorException;
import com.ydf.generator.properties.GeneratorProperties;
import com.ydf.generator.service.ExportService;
import com.ydf.generator.template.TemplateManager;
import com.ydf.generator.template.mybatis.MbgGenerator;
import com.ydf.generator.thread.DatabaseContextHolder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yuandongfei
 * @date 2018/12/13
 */
@Service
public class ExportServiceImpl implements ExportService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TemplateManager templateManager;

    @Autowired
    private GeneratorProperties generatorProperties;

    @Autowired
    private DatabaseContextHolder databaseContextHolder;

    @Autowired
    private DatabaseMemoryCache databaseMemberCache;

    @Autowired
    private GenerateMemoryCache generateMemoryCache;

    @Override
    public File export(String tables) throws Exception {
        if (StringUtils.isBlank(tables)) {
            throw new GeneratorException("需要生成代码的表名不可为空");
        }
        logger.info("### 代码生成工具，tables：{}。开始执行......", tables);
        String[] tabArray = tables.split(",");

        List<GenerateEntity> tableConfigs = generateMemoryCache.selectList(tabArray);

        // 如果是web操作，则输出到临时目录，等待操作压缩
        String sysTemp = System.getProperty("java.io.tmpdir");
        generatorProperties.getTarget().setBaseDir(Paths.get(sysTemp, "code").toString().concat(File.separator));
        if (Files.exists(Paths.get(generatorProperties.getTarget().getBaseDir()))) {
            logger.info("临时存放代码的目录：{}不为空，系统准备删除目录下的所有文件夹及文件", generatorProperties.getTarget().getBaseDir());
            deleteDirectory(Paths.get(generatorProperties.getTarget().getBaseDir()).toFile());
        }
        MbgGenerator.builder()
                .databaseContextHolder(databaseContextHolder)
                .databaseMemberCache(databaseMemberCache)
                .tableConfigs(tableConfigs)
                .targetProperties(generatorProperties.getTarget())
                .build().generate();

        // singleExec:单线程，multiExec: 多线程
        templateManager.exec(tableConfigs);
        logger.info("### 代码生成工具，结束执行......");
        return buildZip();
    }

    private File buildZip() throws Exception {
        logger.info("### 打包ZIP文件工具。开始执行......");
        String baseDir = generatorProperties.getTarget().getBaseDir();
        File dir = new File(baseDir);
        if (dir.exists()) {
            if (dir.isDirectory()) {
                File targetZip = new File(dir.getParent(), "code.zip");
                targetZip.delete();
                try (FileOutputStream fos = new FileOutputStream(targetZip);
                     ZipOutputStream zos = new ZipOutputStream(fos)) {
                    // 遍历目录下的所有文件，并压缩
                    zipFile(dir, zos, null);
                    dir.delete();
                    logger.info("### 删除目录：{}", dir.getCanonicalFile());
                }
                logger.info("### 打包ZIP文件工具，zip文件：{}。结束执行......", targetZip.getCanonicalFile());
                return targetZip;
            }
        }
        return null;
    }

    /**
     * 递归目录下所有文件，按照目录结构进行压缩文件
     *
     * @param file         待压缩的文件或目录
     * @param zos          ZipOutputStream
     * @param zipEntryName 生成的压缩文件文件名
     * @throws IOException
     */
    private void zipFile(File file, ZipOutputStream zos, String zipEntryName) throws IOException {
        logger.info("生成的压缩文件名 zipEntryName：{}", zipEntryName);
        if (file.isDirectory()) {
            //获取目录下所有文件
            File[] files = file.listFiles();
            if (ArrayUtils.isEmpty(files)) {
                if (StringUtils.isBlank(zipEntryName)) {
                    // 生成一个空目录，zipEntry名称必须以/结尾，如果没有则生成空文件
                    zos.putNextEntry(new ZipEntry(String.format("%s/", file.getName())));
                } else {
                    zos.putNextEntry(new ZipEntry(zipEntryName));
                }
                // 没有文件，不需要文件的copy
                // 官方解释：Closes the current ZIP entry and positions the stream for writing the next entry
                zos.closeEntry();
            } else {
                for (File f : files) {
                    String zipEntry;
                    if (StringUtils.isBlank(zipEntryName)) {
                        zipEntry = String.format("%s/%s", file.getName(), f.getName());
                    } else {
                        zipEntry = String.format("%s/%s", zipEntryName, f.getName());
                    }
                    zipFile(f, zos, zipEntry);
                }
            }
        } else {
            zos.putNextEntry(new ZipEntry(zipEntryName));
            int length;
            byte[] buffer = new byte[1024];
            try (FileInputStream fis = new FileInputStream(file)) {
                while ((length = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
            }
        }
        file.delete();
    }

    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            String[] list = directory.list();
            if (ArrayUtils.isNotEmpty(list)) {
                Arrays.stream(list).forEach(s -> {
                    try {
                        deleteDirectory(Paths.get(directory.getCanonicalPath(), s).toFile());
                    } catch (IOException e) {
                        logger.error("删除文件发生异常", e);
                    }
                });
            } else {
                directory.delete();
            }
        }
        directory.delete();
    }
}
