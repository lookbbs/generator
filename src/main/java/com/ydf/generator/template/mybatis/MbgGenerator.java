package com.ydf.generator.template.mybatis;

import com.ydf.generator.cache.DatabaseMemoryCache;
import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.entity.Table;
import com.ydf.generator.properties.GeneratorProperties;
import com.ydf.generator.properties.TargetProperties;
import com.ydf.generator.thread.DatabaseContextHolder;
import lombok.Builder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.ProgressCallback;

import java.nio.file.Paths;
import java.util.List;

/**
 * 调用MyBatis generator 生成基础的代码
 *
 * @author yuandongfei
 * @date 2019/1/11
 */
@Slf4j
@Setter
@Builder
public class MbgGenerator {

    private List<GenerateEntity> tableConfigs;
    private DatabaseMemoryCache databaseMemberCache;
    private DatabaseContextHolder databaseContextHolder;
    private TargetProperties targetProperties;

    public void generate() {
        log.info(">>> MyBatis Generator 开始生成代码.......");
        String basePath = targetProperties.getBaseDir();
        DatabaseDialect dialect = databaseContextHolder.getDatabaseDialect();
        DatabaseConfig databaseConfig = databaseMemberCache.get(dialect.name());

        SqlStatement sqlStatement = SqlStatement.get(databaseConfig.getDialect());

        TargetPackage targetPackage = new TargetPackage();
        targetPackage.setClient(targetProperties.getServer().getPackages().getClient());
        targetPackage.setModel(targetProperties.getServer().getPackages().getModel());

        TargetProject targetProject = new TargetProject();
        targetProject.setClient(Paths.get(basePath, targetProperties.getRoot().getJava()).toString());
        targetProject.setModel(Paths.get(basePath, targetProperties.getRoot().getJava()).toString());
        targetProject.setMap(Paths.get(basePath, targetProperties.getRoot().getResources()).toString());

        MybatisGeneratorBuilder mg = MybatisGeneratorBuilder.builder()
                .tableConfigs(tableConfigs)
                .databaseConfig(databaseConfig)
                .sqlStatement(sqlStatement)
                .targetPackage(targetPackage)
                .targetProject(targetProject)
                .build();
        List<String> warnings = mg.generate(new ProgressCallback() {
            @Override
            public void introspectionStarted(int totalTasks) {
                log.info(">>> ProgressCallback.introspectionStarted: {}", totalTasks);
            }

            @Override
            public void generationStarted(int totalTasks) {
                log.info(">>> ProgressCallback.generationStarted: {}", totalTasks);
            }

            @Override
            public void saveStarted(int totalTasks) {
                log.info(">>> ProgressCallback.saveStarted: {}", totalTasks);
            }

            @Override
            public void startTask(String taskName) {
                log.info(">>> ProgressCallback.startTask: {}", taskName);
            }

            @Override
            public void done() {
                log.info(">>> ProgressCallback.done");
            }

            @Override
            public void checkCancel() {
                log.info(">>> ProgressCallback.checkCancel");
            }
        });
        log.info("MyBatis Generator 生成代码结束，结果如下：");
        for (String warning : warnings) {
            log.info(warning);
        }
    }
}
