package com.ydf.generator.controller;

import com.ydf.generator.exception.GeneratorException;
import com.ydf.generator.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuandongfei
 * @date 2018/12/13
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(GeneratorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneratorExceptionException(GeneratorException e, HttpServletRequest request, HttpServletResponse response) {
        log.error(">>> 请求路径：{}，请求参数：{}", request.getContextPath(), ObjectMapperUtil.writeValueAsString(request.getParameterMap()));
        Map<String, Object> result = new HashMap<>(1);
        result.put("message", e.getMessage());
        return result;
    }
}
