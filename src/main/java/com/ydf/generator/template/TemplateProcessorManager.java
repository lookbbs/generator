package com.ydf.generator.template;

import com.ydf.generator.dto.BuildFileConfig;
import com.ydf.generator.cache.BuildFileConfigMemberCache;
import com.ydf.generator.thread.ThreadManager;
import com.ydf.generator.dto.TableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class TemplateProcessorManager {

    @Autowired
    private List<AbstractTemplate> processorList;

    @Autowired
    private BuildFileConfigMemberCache buildFileConfigMemberCache;

    public List<Future<File>> exec(TableDto data, boolean isWeb) {
        if (!CollectionUtils.isEmpty(processorList)) {
            List<Future<File>> list = new ArrayList<>(processorList.size());
            for (AbstractTemplate processor : processorList) {
                BuildFileConfig buildFileConfig = buildFileConfigMemberCache.get(processor.getName());
                if (null != buildFileConfig && buildFileConfig.getEnable()) {
                    Future<File> submit = ThreadManager.getInstance().submit(() -> processor.process(data, isWeb));
                    list.add(submit);
                }
            }
            return list;
        }
        return null;
    }


}
