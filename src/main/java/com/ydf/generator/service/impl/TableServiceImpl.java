package com.ydf.generator.service.impl;

import com.ydf.generator.dto.ColumnDto;
import com.ydf.generator.dto.TableDto;
import com.ydf.generator.entity.Column;
import com.ydf.generator.entity.Table;
import com.ydf.generator.mapper.TableMapper;
import com.ydf.generator.service.ColumnService;
import com.ydf.generator.service.TableService;
import com.ydf.generator.util.StringTools;
import org.apache.commons.lang3.StringUtils;
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
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private ColumnService columnService;

    @Override
    public List<TableDto> selectList(String schema, String[] tables) {
        List<Table> lst = tableMapper.selectList(schema, tables);
        List<TableDto> result = new ArrayList<>(lst.size());
        for (Table t : lst) {
            TableDto d = new TableDto();
            BeanUtils.copyProperties(t, d);
            List<ColumnDto> columns = columnService.selectList(schema, t.getTableName());
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
            result.add(d);
        }
        return result;
    }
}
