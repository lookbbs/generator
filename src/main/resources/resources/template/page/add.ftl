<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>添加${tableComment!}</title>
${"<#include \"/common/head.ftl\" />"}
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-body">
                 <#if columns??>
                     <#list columns as col>
                         <#if 'PRI' != col.columnKey && col.config.canEdit>
                            <#if col.config.fieldName == "remark">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">${col.config.fieldText}：</label>
                <div class="layui-input-block">
                    <textarea id="${col.config.fieldName!}" name="remark" placeholder="请输入${col.config.fieldText}" class="layui-textarea" title="${col.config.fieldText}"></textarea>
                </div>
            </div>
                            <#else>
            <div class="layui-form-item">
                <label class="layui-form-label">${col.config.fieldText}：</label>
                <div class="layui-input-block">
                                <#if col.javaType == "Date">
                    <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName}" placeholder="请输入${col.config.fieldText}" autocomplete="off" class="layui-input" lay-date='{format:"yyyy-MM-dd"}' title="${col.config.fieldText}" <#if col.nullable =='NO'>lay-verify="required"</#if>>
                                <#else>
                    <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName}" placeholder="请输入${col.config.fieldText}" autocomplete="off" class="layui-input" title="${col.config.fieldText}" <#if col.nullable =='NO'>lay-verify="required"</#if>>
                                </#if>
                </div>
            </div>
                            </#if>
                         </#if>
                     </#list>
                 </#if>

            <div class="layui-form-item">
                <button class="layui-btn layui-btn-normal" lay-filter="save" lay-submit>
                    保存
                </button>
                <button class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${r"${re.contextPath}"}/plugin/js/common/merchant.adapter.layer.form.js"></script>
<script type="text/javascript" src="${r"${re.contextPath}"}/plugin/js/${targetEntityVariableName}/${targetEntityVariableName}Edit.js"></script>
</body>
</html>