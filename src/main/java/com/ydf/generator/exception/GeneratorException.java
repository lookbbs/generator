package com.ydf.generator.exception;

/**
 * @author yuandongfei
 * @date 2018/12/12
 */
public class GeneratorException extends RuntimeException {

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
