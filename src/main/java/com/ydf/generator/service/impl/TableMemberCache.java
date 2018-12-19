package com.ydf.generator.service.impl;

import com.ydf.generator.dto.TableDto;
import com.ydf.generator.service.Cache;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 表对象的内存操作
 *
 * @author yuandongfei
 * @date 2018/12/12
 */
public class TableMemberCache implements Cache<TableDto> {

    private ConcurrentHashMap<String, TableDto> map = new ConcurrentHashMap<>();

    @Override
    public void put(String key, TableDto data) {
        map.put(key, data);
    }

    @Override
    public TableDto get(String key) {
        return map.get(key);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public List<TableDto> selectList(String[] keys) {
        List<TableDto> result = new ArrayList<>(map.size());
        if (ArrayUtils.isNotEmpty(keys)) {
            for (String key : keys) {
                result.add(get(key));
            }
        }
        return result;
    }
}
