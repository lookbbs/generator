package com.ydf.generator.properties;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class TargetProperties {

    /**
     * 生成的文件存储的根目录
     */
    private String baseDir;
    /**
     * 类的基础包路径
     */
    private String basePackage;
    /**
     * 页面配置
     */
    private PageProperties page = new PageProperties();
    /**
     * 服务端配置
     */
    private ServerProperties server = new ServerProperties();
    /**
     * 页面js配置
     */
    private JsProperties js = new JsProperties();

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public PageProperties getPage() {
        return page;
    }

    public void setPage(PageProperties page) {
        this.page = page;
    }

    public ServerProperties getServer() {
        return server;
    }

    public void setServer(ServerProperties server) {
        this.server = server;
    }

    public JsProperties getJs() {
        return js;
    }

    public void setJs(JsProperties js) {
        this.js = js;
    }
}
