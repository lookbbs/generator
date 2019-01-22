layui.use(['form', 'table'], function () {
    const form = layui.form,
        table = layui.table,
        $ = layui.$;

    const url = $("form").attr("action");

    table.render({
        elem: '#configTable',
        url: url,
        page: false,
        loading: true,
        even: true,
        skin: "row",
        limit: 100,
        parseData: function (data) {
            return {
                code: 0,
                data: data
            }
        },
        cols: [[
            {field: 'name', width: 300, title: '文件名'},
            {field: 'enable', width: 300, title: '需要生成', templet: "#switchEnableTpl"},
            {field: 'comment', title: '说明'}
        ]]
    });

    form.on('switch(enableSwitch)', function (data) {
        const name = $(this).data("name") || 0;
        const enable = this.checked;
        const url = data.elem.form.action;
        $.post(url, {name: name, enable: enable}, function (result) {
            console.log(result);
        })
    });

    form.render();
});