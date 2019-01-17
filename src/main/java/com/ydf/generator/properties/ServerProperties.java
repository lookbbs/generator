package com.ydf.generator.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
@Setter
@Getter
public class ServerProperties {
    /**
     * 实体类的包名
     */
    private PackageProperties packages = new PackageProperties();
    /**
     * 控制层配置
     */
    private FileProperties controller = new FileProperties();
    /**
     * 服务层接口配置
     */
    private FileProperties service = new FileProperties();
    /**
     * 服务层接口实现
     */
    private FileProperties serviceImpl = new FileProperties();
    /**
     * 持久化层配置
     */
    private FileProperties dao = new FileProperties();
    /**
     * Mapper.xml配置
     */
    private FileProperties mapper = new FileProperties();
}
