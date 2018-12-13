package com.ydf.generator.controller;

import com.ydf.generator.exception.GeneratorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuandongfei
 * @date 2018/12/13
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(GeneratorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneratorExceptionException(GeneratorException e) {
        Map<String, Object> result = new HashMap<>(1);
        result.put("message", e.getMessage());
        return result;
    }
}
