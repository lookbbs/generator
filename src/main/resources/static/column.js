layui.use(['table'], function () {
    const table = layui.table,
        $ = layui.$;
    const url = $("#columnList").data("url");
    table.render({
        elem: '#columnList',
        url: url,
        parseData: function (data) {
            return {
                code: 0,
                data: data
            }
        },
        cols: [[
            {field: 'columnName', width: 150, title: '列名'},
            {field: 'fieldName', width: 120, title: '字段名', templet: '<div>{{d.config.fieldName}}</div>'},
            {field: 'jdbcType', width: 120, title: '数据类型'},
            {field: 'nullable', width: 100, title: '可为空', templet: '#switchNullableTpl'},
            {field: 'columnComment', width: 250, title: '列名备注'},
            {field: 'showTitle', width: 400, title: '显示的名称', templet: '#fieldTextTpl'},
            {field: 'show', width: 150, title: '列表中显示该列', templet: '#switchShowTpl'},
            {field: 'search', width: 150, title: '是搜索条件', templet: '#switchSearchTpl'},
            {field: 'canEdit', width: 150, title: '可编辑', templet: '#switchCanEditTpl'},
            {field: 'sort', width: 150, title: '可排序', templet: '#switchSortTpl'}
        ]]
    });
});