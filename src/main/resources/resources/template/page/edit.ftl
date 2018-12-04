﻿<!DOCTYPE html>
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
                        <#if col.columnKey?? && 'PRI' == col.columnKey>
            <input value="${r"${record."}${col.fieldName}${r"?string('#')}"}" type="hidden" name="${col.fieldName}">
                         <#elseif col.fieldName == "remark">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">${col.columnComment!}：</label>
                <div class="layui-input-block">
                    <textarea name="remark" placeholder="请输入${col.columnComment!}" class="layui-textarea" title="${col.columnComment!}">${r"${record."}${col.fieldName}${r"}"}</textarea>
                </div>
            </div>
                         <#else>
            <div class="layui-form-item">
                <label class="layui-form-label">${col.columnComment!}：</label>
                <div class="layui-input-block">
                <#if col.jdbcType =='datetime'>
                    <input type="text" name="${col.fieldName!}" value="${r"${record."}${col.fieldName}${r"?string('yyyy-MM-dd')}"}" placeholder="请输入${col.columnComment!}" <#if col.isNullable =='NO'>lay-verify="required"</#if> autocomplete="off" class="layui-input" title="${col.columnComment!}" lay-date='{format:"yyyy-MM-dd"}'>
                <#elseif col.jdbcType="int">
                    <input type="text" name="${col.fieldName!}" value="${r"${record."}${col.fieldName}${r"?string('#')}"}" placeholder="请输入${col.columnComment!}" <#if col.isNullable =='NO'>lay-verify="required"</#if> autocomplete="off" class="layui-input" title="${col.columnComment!}">
                <#else>
                    <input type="text" name="${col.fieldName!}" value="${r"${record."}${col.fieldName}${r"?string}"}" placeholder="请输入${col.columnComment!}" <#if col.isNullable =='NO'>lay-verify="required"</#if> autocomplete="off" class="layui-input" title="${col.columnComment!}">
                </#if>
                </div>
            </div>
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
<script type="text/javascript" src="${r"${re.contextPath}"}/plugin/js/${variableName}/${variableName}Edit.js"></script>
</body>
</html>