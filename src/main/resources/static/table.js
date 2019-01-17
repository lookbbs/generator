layui.use(['table', 'form', 'layer'], function () {
    const form = layui.form,
        table = layui.table,
        $ = layui.$;
    const url = $("#tableList").data("url");
    let loading = layer.load({shade: [0.8, '#000']});
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
        ]],
        done: function (res, curr, count) {
            layer.close(loading);
        }
    });

    table.on('tool(tableList)', function (obj) {
        const row = obj.data;
        if (obj.event === 'edit') {
            const url = $(obj.tr).find("td:last a").data("url");
            layer.full(layer.open({
                type: 2,
                maxmin: true,
                title: '配置' + row.tableName + '的列数据',
                content: url,
                btn: '保存配置',
                btnAlign: 'c', //按钮居中
                yes: function (index, layero) {
                    let field = {},
                        dataArray = [];
                    const body = layui.layer.getChildFrame("body", index);
                    body.find("#columnList").next(".layui-table-view").find(".layui-table-body table tr").each(function () {
                        let t = {};
                        $(this).find("input").each(function () {
                            if (this.type == 'checkbox') {
                                t[this.name] = this.checked;
                            } else {
                                t[this.name] = $(this).val();
                            }
                        })
                        dataArray.push(t);
                    });
                    field.config = {
                        configItems: dataArray
                    };
                    body.find("div.form input").each(function () {
                        field[this.name] = this.value;
                    })
                    console.log(field)

                    const saveUrl = body.find("#columnList").data("url");
                    $.post(saveUrl, {data: JSON.stringify(field)}, function (result) {
                        if (result == 'success') {
                            table.reload("tableList");
                            layui.layer.msg("保存成功", function () {
                                layer.closeAll();
                            });
                        }
                    });
                    return false;

                }
            }));
        }
    });

    //工具栏事件 生成代码按钮点击事件
    table.on('toolbar(tableList)', function (obj) {
        const checkStatus = table.checkStatus(obj.config.id);
        if (!checkStatus.data.length) {
            layer.alert('请勾选需要生成代码的表', {icon: 7});
            return false;
        }
        if (obj.event == 'build') {
            const data = checkStatus.data;
            const tabs = [];
            $(data).each(function () {
                tabs.push(this.tableName);
            });
            const url = $("#exportUrl").attr("href");
            const $form = $('<form></form>');
            $form.attr('action', url + "?rand=" + Math.random());
            $form.attr('method', 'post');

            const $input = $('<input type="hidden" name="tables">');
            $input.attr("value", tabs.join(","));
            $form.append($input);

            $form.appendTo('body').submit().remove();
            return false;
        }
    });
    form.render();
})