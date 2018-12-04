package com.ydf.generator.config;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class TemplateProperties {

    /**
     * 模板存储的根目录
     */
    private String basePackagePath;
    /**
     * 页面模板配置
     */
    private PageProperties page = new PageProperties();

    private JsProperties js = new JsProperties();
    /**
     * 服务端的模板文件配置
     */
    private ServerProperties server = new ServerProperties();

    public String getBasePackagePath() {
        return basePackagePath;
    }

    public void setBasePackagePath(String basePackagePath) {
        this.basePackagePath = basePackagePath;
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
