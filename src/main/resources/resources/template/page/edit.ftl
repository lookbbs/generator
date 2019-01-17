<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>编辑${tableComment!}</title>
${"<#include \"/common/head.ftl\" />"}
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-body">
                 <#if columns??>
                     <#list columns as col>
                         <#if col.columnKey??>
                            <#if 'PRI' == col.columnKey>
            <input id="${col.config.fieldName!}" value="${r"${record."}${col.config.fieldName}${r"?string('#')}"}" type="hidden" name="${col.config.fieldName}">
                            </#if>
                            <#if col.config.canEdit>
                                <#if col.config.fieldName == "remark">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">${col.config.fieldText}：</label>
                <div class="layui-input-block">
                    <textarea name="remark" id="${col.config.fieldName!}" placeholder="请输入${col.config.fieldText}" class="layui-textarea" title="${col.config.fieldText}">${r"${record."}${col.config.fieldName}${r"}"}</textarea>
                </div>
            </div>
                                <#else>
            <div class="layui-form-item">
                <label class="layui-form-label">${col.config.fieldText}：</label>
                <div class="layui-input-block">
                                    <#if col.javaType == 'Date'>
                    <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName!}" value="${r"${(record."}${col.config.fieldName}${r"?string('yyyy-MM-dd'))!}"}" placeholder="请输入${col.config.fieldText}" <#if col.nullable =='NO'>lay-verify="required"</#if> autocomplete="off" class="layui-input" title="${col.config.fieldText}" lay-date='{format:"yyyy-MM-dd"}'>
                                    <#elseif col.javaType == "Integer" || col.javaType == "Long">
                    <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName!}" value="${r"${(record."}${col.config.fieldName}${r"?string('#'))!}"}" placeholder="请输入${col.config.fieldText}" <#if col.nullable =='NO'>lay-verify="required"</#if> autocomplete="off" class="layui-input" title="${col.config.fieldText}">
                                    <#else>
                    <input type="text" id="${col.config.fieldName!}" name="${col.config.fieldName!}" value="${r"${record."}${col.config.fieldName}${r"!}"}" placeholder="请输入${col.config.fieldText}" <#if col.nullable =='NO'>lay-verify="required"</#if> autocomplete="off" class="layui-input" title="${col.config.fieldText}">
                                    </#if>
                </div>
            </div>
                                </#if>
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