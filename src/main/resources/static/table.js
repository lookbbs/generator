layui.use(['table', 'form', 'layer'], function () {
    var form = layui.form;
    form.on('checkbox(allChoose)', function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
        child.each(function (index, item) {
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });

    form.on('submit(config)', function () {
        var $tpl = $('#tplConfig').html()
        layui.layer.open({
            title: '配置需要生成的文件',
            area: ['1024px', '768px'],
            content: $tpl,
            success: function (layero, index) {
                layui.form.render();
            },
            yes: function (index, layero) {
                var data = [];
                $(layero).find("table tr").each(function () {
                    var t = {};
                    $(this).find("input").each(function () {
                        if (this.type == 'checkbox') {
                            t[this.name] = this.checked;
                        } else {
                            t[this.name] = this.value;
                        }
                    });
                    !$.isEmptyObject(t) && data.push(t);
                });
                var url = $(layero).find("form").attr("action");
                $.post(url, {data: JSON.stringify(data)}, function (result) {
                    console.log(result);
                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                })
            }
        });
        return false;
    });

    form.on('submit(generator)', function (data) {
        var child = $("table tbody :checked");
        if (child.length == 0) {
            layer.alert('请勾选需要生成代码的表', {icon: 7});
            return false;
        }
        var tabs = [];
        child.each(function () {
            var d = $(this).data("table");
            if (!!d) {
                tabs.push($(this).data("table"));
            }
        });
        console.log(tabs.join(","))
        var url = $("#exportUrl").attr("href");
        var $form = $('<form></form>');
        $form.attr('action', url + "?rand=" + Math.random());
        $form.attr('method', 'post');

        var $input = $("<input>");
        $input.attr("type", "hidden");
        $input.attr("name", "tables");
        $input.attr("value", tabs.join(","));
        $form.append($input);

        $form.appendTo('body').submit().remove();
        return false;
    });
    form.render();
})