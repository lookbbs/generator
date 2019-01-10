package com.ydf.generator.properties;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class ServerProperties {
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

    public FileProperties getController() {
        return controller;
    }

    public void setController(FileProperties controller) {
        this.controller = controller;
    }

    public FileProperties getService() {
        return service;
    }

    public void setService(FileProperties service) {
        this.service = service;
    }

    public FileProperties getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(FileProperties serviceImpl) {
        this.serviceImpl = serviceImpl;
    }

    public FileProperties getDao() {
        return dao;
    }

    public void setDao(FileProperties dao) {
        this.dao = dao;
    }

    public FileProperties getMapper() {
        return mapper;
    }

    public void setMapper(FileProperties mapper) {
        this.mapper = mapper;
    }
}
