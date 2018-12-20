layui.use(['form'], function () {
    var form = layui.form;

    form.on('submit(save)', function () {
        var dataArray = [];
        $("tr:not(:first)").each(function () {
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

        $.ajax({
            url: document.forms.item(0).action,
            type: document.forms.item(0).method,
            data: {data: data},
            success: function (result) {
                if (result == 'success') {
                    layui.layer.msg("保存成功");
                    window.close();
                }
            }
        });
        return false;
    });
    form.render()
});