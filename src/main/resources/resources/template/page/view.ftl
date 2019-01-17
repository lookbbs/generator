<!DOCTYPE html>
<html lang="en">
<head>
    <title>查看${tableComment!}</title>
${"<#include \"/common/head.ftl\" />"}
    <style type="text/css">
        .layui-form-label {
            background-color: #ddd;
        }

        .layui-input-inline {
            padding: 9px 15px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-body">
                <#if columns??>
                    <#list columns as col>
                <div class="layui-form-item">
                    <label class="layui-form-label">${col.config.fieldText}：</label>
                    <div class="layui-input-block">
                        <label>
                            <#if col.javaType == 'Date'>
                            ${r"${(record."}${col.config.fieldName}${r"?string('yyyy-MM-dd'))!}"}
                            <#elseif col.javaType == "Integer" || col.javaType == "Long">
                            ${r"${(record."}${col.config.fieldName}${r"?string('#'))!}"}
                            <#else>
                            ${r"${record."}${col.config.fieldName}${r"!}"}
                            </#if>
                        </label>
                    </div>
                </div>
                    </#list>
                </#if>
        </div>
    </div>
</div>
</body>
</html>