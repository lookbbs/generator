(function () {
    var pageConfig = {
        table: {
            render: {
                url: getCtxPath() + '/${targetEntityVariableName}/list',
                cols: [[
                    {title: '序号', width: '80', templet: '<div>{{d.LAY_TABLE_INDEX + 1}}</div>', align: 'center'},
            <#if columns??>
                <#list columns as col>
                    <#if col.config.show>
                        <#if col.javaType == 'Date'>
                    {field: '${col.config.fieldName}', title: '${col.config.fieldText}', width: '10%',${col.config.sort?string ("sort: true,","")} align: 'center',templet: '<div>{{ honglu.util.dateFormat(d.${col.config.fieldName}) }}</div>'},
                        <#else>
                    {field: '${col.config.fieldName}', title: '${col.config.fieldText}', width: '10%',${col.config.sort?string ("sort: true,","")} align: 'center'},
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
                    url: getCtxPath() + '/${targetEntityVariableName}/{{id}}'
                },
                edit: {
                    title: "修改${tableComment!}",
                    url: getCtxPath() + '/${targetEntityVariableName}/edit/{{id}}'
                },
                delete: {
                    invoke: function (obj) {
                        var msg = '确定删除[<label style="color: #00AA91;">' + obj.id + '</label>]?';
                        var url = getCtxPath() + '/${targetEntityVariableName}/' + obj.id;
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
                    url: getCtxPath() + '/${targetEntityVariableName}/add'
                });
            }
        }
    }
    if (honglu.adapter.layui.init) {
        honglu.adapter.layui.init(pageConfig);
    }
})();