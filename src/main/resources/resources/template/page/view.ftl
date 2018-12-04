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
                    <label class="layui-form-label">${col.columnComment!}：</label>
                    <div class="layui-input-block">
                        <label>
                            <#if col.jdbcType =='datetime'>
                        ${r"${record."}${col.fieldName}${r"?string('yyyy-MM-dd HH:mm:ss')}"}
                            <#elseif col.jdbcType="int">
                        ${r"${record."}${col.fieldName}${r"?string('#')}"}
                            <#else>
                        ${r"${record."}${col.fieldName}${r"?string}"}
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