layui.use('element', function () {
    const element = layui.element,
        $ = layui.$;

    /**
     * tab操作动作实现
     * @type {{tabAdd: tabAdd, tabChange: tabChange, autoFrameSize: autoFrameSize}}
     */
    const active = {
        /**
         * tab 添加
         * @param config
         */
        tabAdd: function (config) {
            if (!config.url) {
                console.error("菜单项未配置data-url属性");
            }
            const conf = $.extend({id: new Date().getTime(), url: '#', title: '未配置data-title属性', filter: 'demo'}, config);
            element.tabAdd(conf.filter, {
                id: conf.id,
                title: conf.title,
                content: '<iframe data-frameid="' + conf.id + '" frameborder="0" name="content" scrolling="auto" width="100%" src="' + conf.url + '"></iframe>'
            })
            this.autoFrameSize();
        },
        /**
         * tab 切换动作
         * @param config
         */
        tabChange: function (config) {
            const conf = $.extend({id: new Date().getTime(), filter: 'demo'}, config);
            element.tabChange(conf.filter, conf.id);
        },
        /**
         * tab 内容的高度自动适应
         * @param config
         */
        autoFrameSize: function () {
            const h = $(window).height() - 41 - 10 - 60 - 10 - 44 - 10 - 13;
            $("iframe").css("height", h + "px");
        }
    }

    $(".layui-nav a.site-nav-active").on('click', function () {
        const $this = $(this),
            url = $this.data("url"),
            id = $this.data("id") || new Date().getTime(),
            title = $this.data("title") || new Date().getTime();
        const tabs = $(".layui-tab .layui-tab-title li[lay-id='" + id + "']");
        if (!tabs.length) {
            active.tabAdd({id: id, url: url, title: title})
        }
        active.tabChange({id: id});
    });

    element.on('tabDelete(demo)', function (data) {
        console.log("tab closed", data.index)
    })
});