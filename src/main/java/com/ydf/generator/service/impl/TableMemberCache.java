package com.ydf.generator.service.impl;

import com.ydf.generator.dto.TableDto;
import com.ydf.generator.service.TableCache;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 表对象的内存操作
 *
 * @author yuandongfei
 * @date 2018/12/12
 */
public class TableMemberCache implements TableCache {

    private ConcurrentHashMap<String, TableDto> map = new ConcurrentHashMap<>();

    @Override
    public void put(TableDto table) {
        map.put(table.getTableName(), table);
    }

    @Override
    public TableDto get(String tableName) {
        return map.get(tableName);
    }

    @Override
    public void remove(String tableName) {
        map.remove(tableName);
    }

    @Override
    public List<TableDto> selectList(String[] tables) {
        List<TableDto> result = new ArrayList<>(map.size());
        if (ArrayUtils.isNotEmpty(tables)) {
            for (String key : tables) {
                result.add(get(key));
            }
        }
        return result;
    }
}
