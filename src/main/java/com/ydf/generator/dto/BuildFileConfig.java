package com.ydf.generator.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/19
 */
@Getter
@Setter
public class BuildFileConfig {
    private String name;
    private Boolean enable = Boolean.TRUE;
    private String comment;
}
