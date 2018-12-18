(function () {
    var pageConfig = {
        table: {
            render: {
                url: getCtxPath() + '/${variableName}/list',
                cols: [[
                    {field: true, fixed: true, title: '序号', width: '80', templet: '<div>{{d.LAY_TABLE_INDEX + 1}}</div>', align: 'center'},
            <#if columns??>
                <#list columns as col>
                    <#if col.show>
                        <#if col.javaType == 'Date'>
                    {field: '${col.fieldName}', title: '${col.fieldText!}', width: '10%', align: 'center',templet: '<div>{{ honglu.util.dateFormat(d.${col.fieldName}) }}</div>'},
                        <#else>
                    {field: '${col.fieldName}', title: '${col.fieldText!}', width: '10%', align: 'center'},
                        </#if>
                    </#if>
                </#list>
            </#if>
                    {field: 'right', fixed: 'right', title: '操作', width: '20%', align: 'center', toolbar: "#tableOptBar"}
                ]]
            },
            tools: {
                view: {
                    title: "查看${tableComment!}",
                    url: getCtxPath() + '/${variableName}/{{id}}'
                },
                edit: {
                    title: "修改${tableComment!}",
                    url: getCtxPath() + '/${variableName}/edit/{{id}}'
                },
                delete: {
                    invoke: function (obj) {
                        var msg = '确定删除[<label style="color: #00AA91;">' + obj.id + '</label>]?';
                        var url = getCtxPath() + '/${variableName}/' + obj.id;
                        honglu.dialog.confirm(msg, function () {
                            honglu.ajax.delete(url, {}, function (result) {
                                if (result.code == "00000000") {
                                    if (result.data > 0) {
                                        honglu.dialog.alert("删除成功！", function () {
                                            layui.table.reload('dataList');
                                        });
                                    }
                                }
                            });
                        });
                    }
                }
            }
        },
        form: {
            on: []
        },
        active: {
            add: function () {
                honglu.dialog.open({
                    title: "新增",
                    url: getCtxPath() + '/${variableName}/add'
                });
            }
        }
    }
    if (honglu.adapter.layui.init) {
        honglu.adapter.layui.init(pageConfig);
    }
})();