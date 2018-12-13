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
                         <#if col.fieldName == "remark">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">${col.fieldText!}：</label>
                <div class="layui-input-block">
                    <textarea name="remark" placeholder="请输入${col.fieldText!}" class="layui-textarea" title="${col.fieldText!}"></textarea>
                </div>
            </div>
                         <#else>
            <div class="layui-form-item">
                <label class="layui-form-label">${col.fieldText!}：</label>
                <div class="layui-input-block">
                    <#if col.jdbcType=="Date">
                    <input type="text" name="${col.fieldName}" placeholder="请输入${col.fieldText!}" autocomplete="off" class="layui-input" lay-date='{format:"yyyy-MM-dd"}' title="${col.fieldText!}" <#if col.isNullable =='NO'>lay-verify="required"</#if>>
                    <#else>
                    <input type="text" name="${col.fieldName}" placeholder="请输入${col.fieldText!}" autocomplete="off" class="layui-input" title="${col.fieldText!}" <#if col.isNullable =='NO'>lay-verify="required"</#if>>
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