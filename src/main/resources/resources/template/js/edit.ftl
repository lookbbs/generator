(function () {
    var pageConfig = {
        form: {
            url: getCtxPath() + '/${variableName}/save',
            on: [],
            buildData: function (data) {
                return data;
            },
            ajaxSuccess: function (result) {
                if (result.code == "00000000") {
                    if (result.data > 0) {
                        honglu.dialog.alert("保存成功", function () {
                            window.parent.layui.table.reload("dataList");
                            $('#close').click();
                        });
                    }
                }
            }
        }
    }

    if (honglu.adapter.layui.init) {
        honglu.adapter.layui.init(pageConfig);
    }
})();

