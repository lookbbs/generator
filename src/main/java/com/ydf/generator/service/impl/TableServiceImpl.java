package com.ydf.generator.service.impl;

import com.ydf.generator.cache.DatabaseMemoryCache;
import com.ydf.generator.cache.GenerateMemoryCache;
import com.ydf.generator.cache.TableMemoryCache;
import com.ydf.generator.datasource.DatabaseHolder;
import com.ydf.generator.dto.*;
import com.ydf.generator.entity.Column;
import com.ydf.generator.entity.Table;
import com.ydf.generator.exception.GeneratorException;
import com.ydf.generator.properties.GeneratorProperties;
import com.ydf.generator.properties.TargetProperties;
import com.ydf.generator.service.TableService;
import com.ydf.generator.thread.ThreadManager;
import com.ydf.generator.util.ObjectMapperUtil;
import com.ydf.generator.util.StringTools;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Service
public class TableServiceImpl implements TableService {


    @Autowired
    private TableMemoryCache tableMemoryCache;
    @Autowired
    private GenerateMemoryCache generateMemoryCache;
    @Autowired
    private DatabaseHolder databaseHolder;

    @Autowired
    private DatabaseMemoryCache databaseCache;
    @Autowired
    private GeneratorProperties generatorProperties;

    @Override
    public GenerateEntity getByTableName(String tableName) {
        return generateMemoryCache.get(tableName);
    }

    @Override
    public List<Table> selectList(String[] tables) {
        List<Table> result = tableMemoryCache.selectList(tables);
        if (CollectionUtils.isNotEmpty(result)) {
            return result;
        }
        DatabaseConfig db = databaseCache.get();
        result = databaseHolder.findDatabaseDao().selectTableList(db, tables);
        for (Table t : result) {
            List<Column> columns = databaseHolder.findDatabaseDao().selectColumnList(db, t.getTableName(), false);
            List<Column> pks = databaseHolder.findDatabaseDao().selectColumnList(db, t.getTableName(), true);
            t.setColumns(columns);
            t.setPrimaryKeys(pks);
            // 存入缓存
            tableMemoryCache.put(t.getTableName(), t);

            // 初始化GenerateEntity到缓存中
            initEntityCache(t);
        }
        return result;
    }

    /**
     * 初始化缓存
     *
     * @param table
     */
    private void initEntityCache(Table table) {
        List<ConfigItem> items = initConfigItems(table.getColumns());
        Config config = new Config();
        config.setConfigItems(items);
        GenerateEntity entity = new GenerateEntity();
        entity.setData(table);
        entity.setConfig(config);
        entity.setTableName(table.getTableName());
        entity.setTargetEntityClassName(StringTools.upperCamelCase(table.getTableName(), true));
        entity.setTargetEntityVariableName(StringTools.upperCamelCase(table.getTableName()));
        TargetProperties target = generatorProperties.getTarget();
        entity.setBasePackage(target.getBasePackage());
        entity.setTargetEntityPackage(target.getServer().getPackages().getModel());
        entity.setPackageConfig(generatorProperties.getTarget().getServer().getPackages());
        entity.setBasePackagePath(entity.getBasePackage().replaceAll("\\.", "/"));
        entity.setColumns(getColumns(table.getColumns(), items));

        // 存放路径。配置的source目录 + 配置的包路径中将“.”替换成路径分隔符
        entity.setTargetPath(String.format("%s%s/", target.getRoot().getJava(), entity.getTargetEntityPackage().replaceAll("\\.", "/")));
        generateMemoryCache.put(table.getTableName(), entity);
    }

    /**
     * 初始化配置对象的子项列表
     *
     * @param columns
     * @return
     */
    private List<ConfigItem> initConfigItems(List<Column> columns) {
        if (CollectionUtils.isEmpty(columns)) {
            return Collections.EMPTY_LIST;
        }
        List<ConfigItem> result = new ArrayList<>(columns.size());
        columns.forEach(column -> {
            ConfigItem item = new ConfigItem();
            item.setColumnName(column.getColumnName());
            item.setFieldName(StringTools.upperCamelCase(column.getColumnName()));
            // YES: 可为空，NO：不可为空
            item.setNullable(StringUtils.equalsIgnoreCase("YES", column.getNullable()));
            if (StringUtils.isNotBlank(column.getColumnComment())) {
                item.setFieldText(column.getColumnComment());
            } else {
                item.setFieldText(column.getColumnName());
            }
            result.add(item);
        });
        return result;
    }

    @Override
    public String saveColumns(String table, String data) {
        if (StringUtils.isBlank(table)) {
            throw new GeneratorException("请重新选择一个表进行配置");
        }
        if (StringUtils.isBlank(data)) {
            throw new GeneratorException("列信息配置不可空");
        }

        GenerateEntity entity1 = ObjectMapperUtil.convertTo(data, GenerateEntity.class);
        GenerateEntity entityMemory = generateMemoryCache.get(table);
        if (null == entityMemory) {
            generateMemoryCache.put(entity1.getTableName(), entity1);
        } else {
            Config config = entityMemory.getConfig();
            if (null == config) {
                entityMemory.setConfig(entity1.getConfig());
            } else {
                List<ConfigItem> configItems = config.getConfigItems();
                List<ConfigItem> configItemList = entity1.getConfig().getConfigItems();

                Map<String, ConfigItem> map = new HashMap<>(configItemList.size());
                configItemList.forEach(c -> map.put(c.getColumnName(), c));

                // 将内存中的配置更新，都是值引用
                configItems.forEach(item -> {
                    ConfigItem c = map.get(item.getColumnName());
                    item.setSearch(c.getSearch());
                    item.setShow(c.getShow());
                    item.setCanEdit(c.getCanEdit());
                    item.setNullable(c.getNullable());
                    item.setFieldText(c.getFieldText());
                    item.setSort(c.getSort());
                });
                config.setConfigItems(configItems);
                entityMemory.setConfig(config);
                /**
                 * 将最新的配置信息更新模板需要的数据
                 */
                entityMemory.setColumns(getColumns(entityMemory.getData().getColumns(), configItems));
            }
        }
        generateMemoryCache.put(table, entityMemory);
        return "success";
    }

    @Override
    public List<ColumnDto> getColumns(String table) {
        Table tab = tableMemoryCache.get(table);
        if (null == tab) {
            throw new GeneratorException("数据表不存在，请刷新数据库连接...");
        }
        GenerateEntity entity = generateMemoryCache.get(table);
        if (null == entity) {
            throw new GeneratorException("生成实体的配置不存在，请刷新数据库连接...");
        }
        Config config = entity.getConfig();
        if (null == config) {
            throw new GeneratorException("配置数据不存在，请刷新数据库连接...");
        }
        List<ConfigItem> configItems = config.getConfigItems();
        if (CollectionUtils.isEmpty(configItems)) {
            throw new GeneratorException("配置子数据列表不存在，请刷新数据库连接...");
        }
        return getColumns(tab.getColumns(), configItems);
    }

    private List<ColumnDto> getColumns(List<Column> columns, List<ConfigItem> configItems) {
        Map<String, ConfigItem> map = new HashMap<>(configItems.size());
        configItems.forEach(c -> map.put(c.getColumnName(), c));
        List<ColumnDto> result = new ArrayList<>(columns.size());
        columns.forEach(c -> {
            ColumnDto d = new ColumnDto();
            BeanUtils.copyProperties(c, d);
            ConfigItem configItem = map.get(c.getColumnName());
            d.setConfig(configItem);
            result.add(d);
        });
        return result;
    }
}
