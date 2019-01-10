package com.ydf.generator.service;

import com.ydf.generator.dto.DatabaseConfig;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/8
 */
public interface DatabaseService {

    DatabaseConfig getConfig(String dialect);

    void saveConfig(String dialect, DatabaseConfig db);

    List<String> getDatabases(DatabaseConfig db);
}
