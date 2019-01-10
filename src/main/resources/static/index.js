layui.use(['element', 'form', 'laytpl', 'table'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table;


    element.on('tab(tabConfig)', function (data) {
        if (data.index == 1) {
            // 当显示“生成配置”选项卡时，加载内容并显示
            var url = $(data.elem).find(".layui-tab-content .layui-show form")[0].action;
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
        }
    });

    form.on('select(dialect)', function (obj) {
        var url = obj.elem.form.action + '/' + obj.value
        $.getJSON(url, function (result) {
            $("input[data-schema]").change();
            form.val('dbConfig', result);
        })
    })

    form.on('select(schema)', function (obj) {
        console.log(obj)
    })

    form.on('switch(enableSwitch)', function (data) {
        var name = $(this).data("name") || 0;
        var enable = this.checked;
        var url = data.elem.form.action;
        $.post(url, {name: name, enable: enable}, function (result) {
            console.log(result);
        })
    });

    $("input[data-schema]").on('change', function (e) {
        var d = {}, flag = true;
        d.dialect = $("select[name='dialect']").val();
        d.encoding = $("select[name='encoding']").val();
        $("input[data-schema]").each(function () {
            var ds = this.getAttribute("data-schema");
            if (ds == 'required' && !$.trim(this.value)) {
                this.focus();
                flag = false;
                return false;
            }
            d[this.name] = this.value;
        });
        if (flag) {
            var _form = document.forms[0];
            $.getJSON(_form.action, d, function (result) {
                var $schema = $("select[name='schema']");
                $schema.empty();
                $(result).each(function () {
                    $schema.append('<option value="' + this + '">' + this + '</option>');
                });
                form.render("select");
            });
        }
    });

    form.on('submit(save)', function (data) {
        console.log(data);
        $.ajax({
            url: data.form.action,
            method: data.form.method,
            data: data.field,
            success: function (result) {
                if ('ok' == result) {
                    layer.msg("配置保存成功！");
                }
            }
        });
        return false;
    });
    form.render();
    selectTrigger('dialect', 'mysql');
});

/**
 * select标签的事件触发
 * @param name 标签name
 * @param val 选中的值
 */
function selectTrigger(name, val) {
    $("select[name='" + name + "']").next().find('.layui-select-title input').click();
    $("select[name='" + name + "']").next().find('.layui-anim').children('dd[lay-value="' + val + '"]').click();
}