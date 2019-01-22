layui.use(['form'], function () {
    const form = layui.form,
        $ = layui.$;

    form.val('dbConfig', {
        host: '172.16.0.235',
        port: '3306',
        username: 'game',
        password: 'gjz1opsdsbJefcDkbC'
    })

    form.on('select(dialect)', function (obj) {
        const url = obj.elem.form.action + '/' + obj.value
        $.getJSON(url, function (result) {
            $("input[data-schema]").change();
            form.val('dbConfig', result);
        })
    })

    $("input[data-schema]").on('focus', function () {
        let d = {}, flag = true;
        d.dialect = $("select[name='dialect']").val();
        d.encoding = $("select[name='encoding']").val();
        $("input[data-schema]").each(function () {
            const ds = this.getAttribute("data-schema");
            if (ds == 'required' && !$.trim(this.value)) {
                this.focus();
                flag = false;
                return false;
            }
            d[this.name] = this.value;
        });
        if (flag) {
            const _form = document.forms[0];
            $.getJSON(_form.action, d, function (result) {
                const $schema = $("select[name='schema']");
                $schema.empty();
                if (Array.isArray(result)) {
                    $(result).each(function () {
                        $schema.append('<option value="' + this + '">' + this + '</option>');
                    });
                }
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