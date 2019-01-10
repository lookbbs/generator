package com.ydf.generator.service.impl;

import com.ydf.generator.cache.BuildFileConfigMemberCache;
import com.ydf.generator.cache.DatabaseMemberCache;
import com.ydf.generator.datasource.DatabaseHolder;
import com.ydf.generator.dto.*;
import com.ydf.generator.entity.Column;
import com.ydf.generator.entity.Table;
import com.ydf.generator.exception.GeneratorException;
import com.ydf.generator.cache.Cache;
import com.ydf.generator.service.ColumnService;
import com.ydf.generator.service.TableService;
import com.ydf.generator.util.ObjectMapperUtil;
import com.ydf.generator.util.StringTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private Cache<TableDto> tableCache;

    @Autowired
    private BuildFileConfigMemberCache buildFileConfigMemberCache;

    @Autowired
    private DatabaseHolder databaseHolder;

    @Autowired
    private DatabaseMemberCache databaseCache;

    @Override
    public List<TableDto> selectList(String[] tables) {
        List<TableDto> result = tableCache.selectList(tables);
        if (!CollectionUtils.isEmpty(result)) {
            return result;
        }
        DatabaseConfig db = databaseCache.get();
        List<Table> lst = databaseHolder.findDatabaseDao().selectTableList(db, tables);
        for (Table t : lst) {
            TableDto d = tableCache.get(t.getTableName());
            if (null == d) {
                d = new TableDto();
                BeanUtils.copyProperties(t, d);
                List<ColumnDto> columns = columnService.selectList(t.getTableName());
                if (!CollectionUtils.isEmpty(columns)) {
                    for (Column c : columns) {
                        if (StringUtils.equalsIgnoreCase(c.getColumnKey(), "PRI")) {
                            d.setPrimaryKey(c);
                            break;
                        }
                    }
                }
                d.setColumns(columns);
                d.setVariableName(StringTools.upperCamelCase(t.getTableName()));
                d.setEntityName(StringTools.upperCamelCase(t.getTableName(), true));
            }
            result.add(d);
            tableCache.put(d.getTableName(), d);
        }
        return result;
    }

    @Override
    public String saveColumn(String table, String data) throws IOException {
        if (StringUtils.isBlank(table)) {
            throw new GeneratorException("请重新选择一个表进行配置");
        }
        if (StringUtils.isBlank(data)) {
            throw new GeneratorException("列信息配置不可空");
        }
        TableDto tab = tableCache.get(table);
        if (null == tab) {
            // 初始化数据
            selectList(new String[]{table});
            tab = tableCache.get(table);
            if (null == tab) {
                throw new GeneratorException(table + "表不存在");
            }
        }
        List<ColumnConfig> configList = ObjectMapperUtil.convertToList(data, ColumnConfig.class);
        List<ColumnDto> columns = tab.getColumns();
        for (ColumnDto s : columns) {
            for (ColumnConfig c : configList) {
                if (StringUtils.equalsIgnoreCase(s.getColumnName(), c.getColumnName())) {
                    s.setSearch(c.getSearch());
                    s.setShow(c.getShow());
                    s.setCanEdit(c.getCanEdit());
                    if (c.getNullable()) {
                        s.setNullable("YES");
                    } else {
                        s.setNullable("NO");
                    }
                    s.setFieldText(c.getFieldText());
                    break;
                }
            }
        }
        tab.setColumns(columns);
        tableCache.put(tab.getTableName(), tab);
        return "success";
    }

    @Override
    public List<BuildFileConfig> getConfigList() {
        return buildFileConfigMemberCache.selectList(null);
    }

    @Override
    public void saveConfig(BuildFileConfig config) {
        BuildFileConfig c = buildFileConfigMemberCache.get(config.getName());
        c.setEnable(config.getEnable());
        buildFileConfigMemberCache.put(config.getName(), c);
    }
}
