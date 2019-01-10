package com.ydf.generator.properties;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class JsProperties {
    /**
     * 列表页的js
     */
    private FileProperties list = new FileProperties();

    /**
     * 编辑页的js
     */
    private FileProperties edit = new FileProperties();

    public FileProperties getList() {
        return list;
    }

    public void setList(FileProperties list) {
        this.list = list;
    }

    public FileProperties getEdit() {
        return edit;
    }

    public void setEdit(FileProperties edit) {
        this.edit = edit;
    }
}
