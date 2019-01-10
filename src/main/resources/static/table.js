layui.use(['table', 'form', 'layer', 'table'], function () {
    var form = layui.form,
        table = layui.table;
    var url = $("#tableList").data("url");
    table.render({
        elem: '#tableList',
        url: url,
        toolbar: '#toolbarTpl',
        defaultToolbar: false,
        initSort: {field: 'tableName', type: 'asc'},
        page: false,
        loading: true,
        even: true,
        skin: "row",
        limit: 1000,
        parseData: function (data) {
            return {
                code: 0,
                data: data
            }
        },
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'tableSchema', width: 300, title: 'Schema'},
            {field: 'tableName', width: 300, sort: true, title: 'Table'},
            {field: 'tableComment', width: 300, title: '备注'},
            {fixed: 'right', title: '操作', toolbar: "#operatorTpl"}
        ]]
    });

    table.on('tool(tableList)', function (obj) {
        var row = obj.data;
        if (obj.event === 'edit') {
            var url = $(obj.tr).find("td:last a").data("url");
            layer.full(layer.open({
                type: 1,
                maxmin: true,
                title: '配置' + row.tableName + '的列数据',
                content: '<table id="columnList" class="layui-table" lay-filter="columnList"/>',
                btn: '保存配置',
                btnAlign: 'c', //按钮居中
                yes: function () {
                    var dataArray = [];
                    $("#columnList").next(".layui-table-view").find(".layui-table-body table tr").each(function () {
                        var t = {};
                        $(this).find("input").each(function () {
                            if (this.type == 'checkbox') {
                                t[this.name] = this.checked;
                            } else {
                                t[this.name] = $(this).val();
                            }
                        })
                        dataArray.push(t);
                    });
                    var data = JSON.stringify(dataArray);
                    console.log(data)
                    $.post(url, {data: data}, function (result) {
                        if (result == 'success') {
                            table.reload("tableList");
                            layui.layer.msg("保存成功", function () {
                                layer.closeAll();
                            });
                        }
                    });
                    return false;
                },
                success: function (layero, index) {
                    console.log(layero, index);
                    table.render({
                        elem: '#columnList',
                        data: row.columns,
                        cols: [[{
                            field: 'columnComment',
                            width: 200,
                            title: '列名备注'
                        }, {
                            field: 'columnName',
                            width: 150,
                            sort: true,
                            title: '列名'
                        }, {
                            field: 'jdbcType',
                            width: 120,
                            title: '数据类型'
                        }, {
                            field: 'nullable',
                            width: 100,
                            title: '为空',
                            templet: function (d) {
                                if (d.nullable == 'YES') {
                                    return '<input type="checkbox" name="nullable" lay-skin="switch" lay-text="是|否" checked>';
                                } else {
                                    return '<input type="checkbox" name="nullable" lay-skin="switch" lay-text="是|否">';
                                }
                            }
                        }, {
                            field: 'fieldName',
                            width: 120,
                            title: '字段名'
                        }, {
                            field: 'showTitle',
                            width: 400,
                            title: '显示的名称',
                            templet: function (d) {
                                var v = d.fieldText ? d.fieldText : d.columnComment;
                                return '<input type="text" name="fieldText" value="' + v + '" placeholder="请输入' + d.columnComment + '" autocomplete="off" class="layui-input">';
                            }
                        }, {
                            field: 'show',
                            width: 200,
                            title: '列表中显示该列',
                            templet: function (d) {
                                var r = '<input type="hidden" name="columnName" value="' + d.columnName + '"/>';
                                if (d.show) {
                                    return '<input type="checkbox" name="show" lay-skin="switch" lay-text="是|否" checked> ' + r
                                } else {
                                    return '<input type="checkbox" name="show" lay-skin="switch" lay-text="是|否" > ' + r
                                }
                            }
                        }, {
                            field: 'search',
                            width: 200,
                            title: '是搜索条件',
                            templet: function (d) {
                                if (d.search) {
                                    return '<input type="checkbox" name="search" lay-skin="switch" lay-text="是|否" checked>'
                                } else {
                                    return '<input type="checkbox" name="search" lay-skin="switch" lay-text="是|否" >'
                                }
                            }
                        }, {
                            field: 'canEdit',
                            width: 200,
                            title: '编辑该列',
                            templet: function (d) {
                                if (d.canEdit) {
                                    return '<input type="checkbox" name="canEdit" lay-skin="switch" lay-text="是|否" checked>'
                                } else {
                                    return '<input type="checkbox" name="canEdit" lay-skin="switch" lay-text="是|否">'
                                }
                            }
                        }]]
                    });
                }
            }));
        }
    });

    //工具栏事件 生成代码按钮点击事件
    table.on('toolbar(tableList)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        if (!checkStatus.data.length) {
            layer.alert('请勾选需要生成代码的表', {icon: 7});
            return false;
        }
        if (obj.event == 'build') {
            var data = checkStatus.data;
            var tabs = [];
            $(data).each(function () {
                tabs.push(this.tableName);
            });
            var url = $("#exportUrl").attr("href");
            var $form = $('<form></form>');
            $form.attr('action', url + "?rand=" + Math.random());
            $form.attr('method', 'post');

            var $input = $('<input type="hidden" name="tables">');
            $input.attr("value", tabs.join(","));
            $form.append($input);

            $form.appendTo('body').submit().remove();
            return false;
        }
    });
    form.render();
})