package com.ydf.generator.config;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */
public class PageProperties {
    /**
     * 列表页面
     */
    private FileProperties list = new FileProperties();
    /**
     * 新增页面
     */
    private FileProperties add = new FileProperties();
    /**
     * 编辑页面
     */
    private FileProperties edit = new FileProperties();
    /**
     * 详情页面
     */
    private FileProperties detail = new FileProperties();

    public FileProperties getList() {
        return list;
    }

    public void setList(FileProperties list) {
        this.list = list;
    }

    public FileProperties getAdd() {
        return add;
    }

    public void setAdd(FileProperties add) {
        this.add = add;
    }

    public FileProperties getEdit() {
        return edit;
    }

    public void setEdit(FileProperties edit) {
        this.edit = edit;
    }

    public FileProperties getDetail() {
        return detail;
    }

    public void setDetail(FileProperties detail) {
        this.detail = detail;
    }
}
