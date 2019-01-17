package com.ydf.generator.cache;

import com.ydf.generator.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuandongfei
 * @date 2019/1/8
 */
@Slf4j
public abstract class AbstractMemoryCache<T> implements Cache<T> {

    @Autowired
    private HttpSession session;

    private Map<String, T> map = new HashMap<>();

    @Override
    public Boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void put(String key, T data) {
        log.info(">>> 对象存储到内存中，key：{}", getKey(key));
        map.put(getKey(key), data);
    }

    @Override
    public T get(String key) {
        return map.get(getKey(key));
    }

    @Override
    public void remove(String key) {
        log.info(">>> 删除内存中的对象，key：{}", getKey(key));
        map.remove(getKey(key));
    }

    @Override
    public void removeAll() {
        map.clear();
    }

    @Override
    public List<T> selectList(String[] keys) {
        List<T> result = new ArrayList<>(map.size());
        if (ArrayUtils.isNotEmpty(keys)) {
            for (String key : keys) {
                result.add(get(key));
            }
        } else {
            map.forEach((k, v) -> result.add(v));
        }
        return result;
    }

    private String getKey(String key) {
        String className = getClass().getSimpleName();
        String sessionId = session.getId();
        return String.format("%s:%s:%s", sessionId, className, key).toUpperCase();
    }
}
