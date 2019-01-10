package com.ydf.generator.cache;

import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/1/10
 */
@Component
public class BuildFileConfigMemberCache extends AbstractCache<BuildFileConfig> {

    @Autowired
    private List<Template> templates;

    private void init() {
        if (isEmpty()) {
            synchronized (templates) {
                if (isEmpty()) {
                    for (Template t : templates) {
                        BuildFileConfig c = new BuildFileConfig();
                        c.setComment(t.getCommon());
                        c.setName(t.getName());
                        put(t.getName(), c);
                    }
                }
            }
        }
    }

    @Override
    public BuildFileConfig get(String key) {
        init();
        return super.get(key);
    }

    @Override
    public List<BuildFileConfig> selectList(String[] keys) {
        init();
        return super.selectList(keys);
    }
}
