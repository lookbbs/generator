package com.ydf.generator.service.impl;

import com.ydf.generator.cache.DatabaseMemberCache;
import com.ydf.generator.datasource.DatabaseHolder;
import com.ydf.generator.dto.ColumnDto;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.dto.TableDto;
import com.ydf.generator.entity.Column;
import com.ydf.generator.cache.Cache;
import com.ydf.generator.service.ColumnService;
import com.ydf.generator.util.JdbcTypeTool;
import com.ydf.generator.util.StringTools;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private Cache<TableDto> tableCache;
    @Autowired
    private DatabaseHolder databaseHolder;
    @Autowired
    private DatabaseMemberCache databaseCache;

    @Override
    public List<ColumnDto> selectList(String table) {
        TableDto tableDto = tableCache.get(table);
        if (null != tableDto && !CollectionUtils.isEmpty(tableDto.getColumns())) {
            return tableDto.getColumns();
        }
        DatabaseConfig db = databaseCache.get();
        List<Column> lst = databaseHolder.findDatabaseDao().selectColumnList(db, table);

        List<ColumnDto> result = new ArrayList<>(lst.size());
        for (Column c : lst) {
            ColumnDto d = new ColumnDto();
            BeanUtils.copyProperties(c, d);
            d.setFieldName(StringTools.upperCamelCase(c.getColumnName()));
            d.setJavaType(JdbcTypeTool.getJavaType(c.getJdbcType()));
            result.add(d);
        }
        return result;
    }
}
