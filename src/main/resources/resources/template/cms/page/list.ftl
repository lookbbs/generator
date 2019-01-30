<!DOCTYPE html>
<html>
<head>
${"<#include \"/common/head.ftl\" />"}
    <title>${tableComment!}</title>
    <style type="text/css">
        .layui-input {
            height: 38px;
            width: 100%;
        }
    </style>
</head>

<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header">
            <div class="layui-form-item">
                <#if columns??>
                    <#list columns as col>
                        <#if col.config.search>
                <div class="layui-inline">
                    <label class="layui-form-label">${col.config.fieldText}：</label>
                    <div class="layui-input-inline">
                        <#if col.javaType=="Date">
                            <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName}" placeholder="请输入${col.config.fieldText}" autocomplete="off" class="layui-input" lay-date='{format:"yyyy-MM-dd"}'>
                        <#else>
                            <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName}" placeholder="请输入${col.config.fieldText}" autocomplete="off" class="layui-input">
                        </#if>
                    </div>
                </div>
                        </#if>
                    </#list>
                </#if>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn icon-position-button" lay-submit="" lay-filter="search" data-type="select">
                        <i class="layui-icon layui-icon-search">&#xe615;</i>
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-card-body">
            <div style="padding-bottom: 10px;">
                <button class="layui-btn opt-btn" data-type="add">
                    <i class="layui-icon layui-icon-add-1">&#xe654;</i>添加
                </button>
            </div>
            <table id="dataList" class="layui-hide" lay-filter="dataList"></table>
        </div>
    </div>
</div>

<script type="text/html" id="tableOptBar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="view"><i class="layui-icon layui-icon-form">&#xe63c;</i>查看</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit">&#xe642;</i>编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-list">&#xe60a;</i>删除</a>
</script>

<script type="text/javascript" src="${r"${re.contextPath}"}/plugin/js/common/merchant.adapter.layer.table.js"></script>
<script type="text/javascript" src="${r"${re.contextPath}"}/plugin/js/${targetEntityVariableName}/${targetEntityVariableName}List.js"></script>
</body>
</html>
