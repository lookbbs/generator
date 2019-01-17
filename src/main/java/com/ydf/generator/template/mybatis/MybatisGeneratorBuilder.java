package com.ydf.generator.template.mybatis;

import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.dto.GenerateEntity;
import com.ydf.generator.entity.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis generator 代理类
 *
 * @author yuandongfei
 * @date 2019/1/11
 */
@Getter
@Setter
@Slf4j
@Builder
public class MybatisGeneratorBuilder {

    /**
     * 目标package配置
     */
    private TargetPackage targetPackage;
    /**
     * 目标存储目录
     */
    private TargetProject targetProject;

    private SqlStatement sqlStatement;

    private List<GenerateEntity> tableConfigs;
    /**
     * 数据库的配置
     */
    private DatabaseConfig databaseConfig;

    private boolean overwrite = true;

    /**
     * 生成代码
     *
     * @param callback
     * @throws Exception
     */
    public List<String> generate(ProgressCallback callback) {
        // 重置
        resetDirectory(Paths.get(targetProject.getClient()));
        resetDirectory(Paths.get(targetProject.getMap()));

        Configuration configuration = buildConfiguration();

        ShellCallback shellCallback = new DefaultShellCallback(overwrite);
        List<String> warnings = new ArrayList<>();
        try {
            MyBatisGenerator generator = new MyBatisGenerator(configuration, shellCallback, warnings);
            generator.generate(callback);
        } catch (Exception e) {
            log.error("mybatis generator process error！", e);
        }
        return warnings;
    }

    /**
     * 配置generator xml配置项
     *
     * @return
     */
    private Configuration buildConfiguration() {
        Configuration config = new Configuration();
        Context context = buildContext();
        config.addContext(context);
        return config;
    }

    /**
     * 配置generator xml的context配置项
     *
     * @return
     */
    private Context buildContext() {
        Context context = new Context(ModelType.CONDITIONAL);
        // MyBatis3 or MyBatis3Simple
        context.setTargetRuntime("MyBatis3");
        context.setId("defaultContext");
        //自动识别数据库关键字，默认false，如果设置为true，
        //根据SqlReservedWords中定义的关键字列表；一般保留默认值，遇到数据库关键字（Java关键字），
        //使用columnOverride覆盖
        context.addProperty(PropertyRegistry.CONTEXT_AUTO_DELIMIT_KEYWORDS, "true");
        // 生成的Java文件的编码
        context.addProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING, "utf-8");

        // beginningDelimiter和endingDelimiter：指明数据库的用于标记数据库对象名的符号，比如ORACLE就是双引号，MYSQL默认是`反引号
        DatabaseDialect dialect = DatabaseDialect.getDatabaseDialect(databaseConfig.getDialect());
        if (null != dialect) {
            if (StringUtils.isNotBlank(dialect.getBeginningDelimiter())) {
                context.addProperty("beginningDelimiter", dialect.getBeginningDelimiter());
            }
            if (StringUtils.isNotBlank(dialect.getEndingDelimiter())) {
                context.addProperty("endingDelimiter", dialect.getEndingDelimiter());
            }
        }

        // 格式化java代码
        context.addProperty("javaFormatter", "org.mybatis.generator.api.dom.DefaultJavaFormatter");
        // 格式化xml代码
        context.addProperty("xmlFormatter", "org.mybatis.generator.api.dom.DefaultXmlFormatter");

        //格式化信息
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        context.addPluginConfiguration(pluginConfiguration);

        //设置是否生成注释
        CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType(DbRemarksCommentGenerator.class.getName());
        commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
        commentGeneratorConfiguration.addProperty("suppressDate", "true");
        commentGeneratorConfiguration.addProperty("columnRemarks", "true");
        commentGeneratorConfiguration.addProperty("annotations", "false");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

        //实体添加序列化
        PluginConfiguration serializablePluginConfiguration = new PluginConfiguration();
        serializablePluginConfiguration.addProperty("type", "org.mybatis.generator.plugins.SerializablePlugin");
        serializablePluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        context.addPluginConfiguration(serializablePluginConfiguration);

        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        //是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.）
        javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

        //设置连接数据库
        JDBCConnectionConfiguration jdbcConnectionConfiguration = getJdbcConnectionConfiguration();
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        //生成实体类的地址
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = getJavaModelGeneratorConfiguration();
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        //生成Mapper.xml的地址
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = getSqlMapGeneratorConfiguration();
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        //生成Mapper.xml对应client，也就是接口dao(Mapper.java)
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = getJavaClientGeneratorConfiguration();
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        // table配置
        List<TableConfiguration> tableConfigurations = getTableConfigurationList(context);
        if (CollectionUtils.isNotEmpty(tableConfigurations)) {
            for (TableConfiguration t : tableConfigurations) {
                context.addTableConfiguration(t);
            }
        }

        return context;
    }

    private List<TableConfiguration> getTableConfigurationList(Context context) {
        List<TableConfiguration> list = new ArrayList<>();
        for (GenerateEntity t : tableConfigs) {
            list.add(getTableConfiguration(context, t));
        }
        return list;
    }


    /**
     * 配置单个Table
     *
     * @param context
     * @return
     */
    private TableConfiguration getTableConfiguration(Context context, GenerateEntity tableConfig) {
        Table table = tableConfig.getData();
        TableConfiguration config = new TableConfiguration(context);
        config.setTableName(table.getTableName());
        if (StringUtils.isNotBlank(tableConfig.getTargetEntityClassName())) {
            config.setDomainObjectName(tableConfig.getTargetEntityClassName());
        }
        log.warn(">>> Mysql 数据库连接需要配置table的catalog，schema属性，其它数据库待验证");
        if (table.hasPrimaryKey() && table.getPrimaryKeys().size() == 1) {
            GeneratedKey generatedKey = new GeneratedKey(table.getPrimaryKeys().get(0).getColumnName(), sqlStatement.name(), true, null);
            config.setGeneratedKey(generatedKey);
        }
        return config;
    }

    /**
     * 生成mapxml对应client，也就是接口dao
     *
     * @return
     */
    private JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration() {
        JavaClientGeneratorConfiguration config = new JavaClientGeneratorConfiguration();
        config.setTargetPackage(targetPackage.getClient());
        config.setTargetProject(targetProject.getClient());
        config.setConfigurationType("XMLMAPPER");
        config.addProperty("enableSubPackages", "false");
        return config;
    }

    /**
     * 生成的xml的地址
     *
     * @return
     */
    private SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration() {
        SqlMapGeneratorConfiguration config = new SqlMapGeneratorConfiguration();
        config.setTargetProject(targetProject.getMap());
        config.setTargetPackage(targetPackage.getMap());
        config.addProperty("enableSubPackages", "false");
        return config;
    }

    /**
     * 生成实体类的地址
     *
     * @return
     */
    private JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
        JavaModelGeneratorConfiguration config = new JavaModelGeneratorConfiguration();
        config.setTargetPackage(targetPackage.getModel());
        config.setTargetProject(targetProject.getModel());
        config.addProperty("enableSubPackages", "false");
        config.addProperty("trimStrings", "true");
        return config;
    }

    /**
     * 配置数据库连接
     *
     * @return
     */
    private JDBCConnectionConfiguration getJdbcConnectionConfiguration() {
        JDBCConnectionConfiguration config = new JDBCConnectionConfiguration();
        config.setDriverClass(databaseConfig.getDriverClass());
        config.setConnectionURL(databaseConfig.getUrl());
        config.setPassword(databaseConfig.getPassword());
        config.setUserId(databaseConfig.getUsername());
        return config;
    }

    /**
     * 重置目录
     *
     * @param path
     * @return
     */
    private String resetDirectory(Path path) {
        try {
            clear(path);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            log.info(">>> directory: {}", path);
        } catch (IOException e) {
            log.error(">>> reset directory error", e);
        }
        return path.toString();
    }

    /**
     * 清理目录下的所有文件
     *
     * @param path 待清理的目录
     * @return 目录全路径
     */
    private void clear(Path path) {
        try {
            if (Files.isDirectory(path)) {
                Files.list(path).forEach(this::clear);
            } else {
                if (Files.exists(path)) {
                    Files.delete(path);
                    log.info(">>> delete file: {}", path);
                }
            }
        } catch (IOException e) {
            log.error(">>> clear directory error", e);
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("d:\\data", "src/main/java");
        System.out.println(path);
    }

}
