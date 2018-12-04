package com.ydf.generator.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydf.generator.config.ThreadManager;
import com.ydf.generator.dto.TableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Component
public class TemplateProcessorManager {

    @Autowired
    private List<AbstractTemplateProcessor> processorList;

    public void exec(TableDto data) {
        if (!CollectionUtils.isEmpty(processorList)) {
            for (AbstractTemplateProcessor processor : processorList) {
                ThreadManager.getInstance().execute(() -> {
                    try {
                        processor.process(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
