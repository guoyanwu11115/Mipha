<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/29
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>MiphaLink Group</title>
        <!-- easyui组件 -->
        <link rel="stylesheet" type="text/css" href="component/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="component/easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="component/easyui/themes/icon-defined.css">
        <script type="text/javascript" src="component/easyui/jquery.min.js"></script>
        <script type="text/javascript" src="component/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="component/easyui/locale/easyui-lang-zh_CN.js"></script>
    </head>
    <body class="easyui-layout">
        <!-- 上部页面 -->
        <div data-options="region:'north',href:'jsps/common/north.jsp',border:false" style="height:60px;background:#99cdff;padding:10px">north region</div>
        <!-- 下部页面 -->
        <div data-options="region:'south',href:'jsps/common/south.jsp',border:false" style="height:30px;background:#8d8d8d;padding:10px;">south region</div>
        <!-- 中部页面 -->
        <div data-options="region:'center',border:false">
            <div style="width:100%;height:100%;position: relative;">
                <div id="LeftMenuDiv" style="width: 202px;height:100%;display: inline-block; background-color: green;">
                    <div class="easyui-panel" title="菜单列表" style="width:100%;height:100%;overflow-x:hidden;overflow-y:auto;" data-options="onOpen:onPanelOpen">
                        <div id="LeftMenu" style="">
                        </div>
                    </div>
                </div>
                <div id="mainTab" class="easyui-tabs" data-options="fit:true,onSelect:onTabSelect" style="display: inline-block; position: absolute;">
                    <div title="首页" data-options="closable:false" style="overflow:hidden;background-color:#fff;">
                        <iframe scrolling="auto" frameborder="0" src="jsps/home.jsp" style="width:100%;height:100%"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
    var menuNodes;
    $(function () {
        $.get("menus.html",{},function (data) {
            menuNodes = $.parseJSON(data) //menu转换json对象
            //手动添加"首页"mune
            var main = {
                text: 'HOME',
                iconCls: "icon-defined-home",
                state: 'open',
                children: [{
                    text: '首页',
                    iconCls : 'icon-blank'
                }]};
            //添加到数组第一位
            menuNodes.unshift(main);
            if(menuNodes){
                $('#LeftMenu').sidemenu({
                    data:menuNodes,
                    onSelect: onSideMenuSelect,    //打开标签页
                    border: false
                });
            }
        });
    });
    //打开标签页
    function onSideMenuSelect(item) {
        if (!$('#mainTab').tabs('exists', item.text)) {
            $('#mainTab').tabs('add', {
                title: item.text,
                content: '<iframe scrolling="auto" frameborder="0"  src="' + item.url + '" style="width:100%;height:99%;"></iframe>',
                closable: true,
                icon: item.iconCls,
                id: item.id
            });
        } else {
            $('#mainTab').tabs('select', item.text);
        }
        addTabMenu();
    }

    function addTabMenu() {
        /* 双击关闭TAB选项卡 */
        $(".tabs-inner").dblclick(function () {
            var subtitle = $(this).children(".tabs-closable").text();
            $('#mainTab').tabs('close', subtitle);
        });
        /* 为选项卡绑定右键 */
        $(".tabs-inner").bind('contextmenu', function (e) {
            $('#tab_menu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
            var subtitle = $(this).children(".tabs-closable").text();
            $('#tab_menu').data("currtab", subtitle);
            $('#mainTab').tabs('select', subtitle);
            return false;
        });
    }

    function onPanelOpen() {
        var panel = $(this);
        var _1e = panel.panel("header").children("div.panel-tool");
        _1e.children("a.panel-tool-collapse").hide();

        var _20 = "layout-button-left";// + _1f[dir];
        var t = _1e.children("a." + _20);
        if (!t.length) {
            t = $("<a href=\"javascript:;\"></a>").addClass(_20).appendTo(_1e);
            t.bind("click", { dir: "left" }, function (e) {
                if (e.target.className == "layout-button-right") {
                    e.target.className = "layout-button-left"
                    onWestExpand();
                    panel.panel("setTitle", panel.titleTemp);
                    panel.panel('resize', {
                        width: 202
                    });

                    var leftMenuDiv = $('#LeftMenuDiv');
                    leftMenuDiv.each(function () {
                        this.style.width = '202px';
                    });
                }
                else {
                    e.target.className = "layout-button-right"
                    onWestCollapse();
                    var opt = panel.panel('options');
                    panel.titleTemp = opt.title;
                    panel.panel("setTitle", "");
                    panel.panel('resize', {
                        width: 42
                    });

                    var leftMenuDiv = $('#LeftMenuDiv');
                    leftMenuDiv.each(function () {
                        this.style.width = '42px';
                    });
                }

                return false;
            });
        }
        //$(this).panel("options").collapsible ? t.show() : t.hide();
    }

    function onTabSelect(title, index) {
        var tabs = $('#mainTab');
        var tab = tabs.tabs('getTab', index);
        var menus = $('#LeftMenu');
        if (menus.hasClass('sidemenu')) {
            var opts = menus.sidemenu("options");
            changeMenuSelect(menus, opts, tab[0].id);
        }
    }

    function onWestCollapse() {
        var opts = $('#LeftMenu').sidemenu('options');
        if (opts.collapsed != 'collapse') {
            $('#LeftMenu').sidemenu('collapse');
            $('#LeftMenu').sidemenu('resize', {
                width: 40
            });
        }
    }

    function onWestExpand() {
        var opts = $('#LeftMenu').sidemenu('options');
        if (opts.collapsed != 'expand') {
            $('#LeftMenu').sidemenu('expand');
            $('#LeftMenu').sidemenu('resize', {
                width: 200
            });
        }
    }





    function changeMenuSelect(menus, opts, selectId) {
        var menutrees = menus.find(".sidemenu-tree");
        menutrees.each(function () {
            var menuItem = $(this);
            changeMenuStyle(menuItem, opts, selectId);
        });

        var tooltips = menus.find(".tooltip-f");
        tooltips.each(function () {
            var menuItem = $(this);
            var tip = menuItem.tooltip("tip");
            if (tip) {
                tip.find(".sidemenu-tree").each(function () {
                    changeMenuStyle($(this), opts, selectId);
                });
                menuItem.tooltip("reposition");
            }
        });
    }

    function changeMenuStyle(menuItem, opts, selectId) {
        menuItem.find("div.tree-node-selected").removeClass("tree-node-selected");
        var node = menuItem.tree("find", selectId);
        if (node) {
            $(node.target).addClass("tree-node-selected");
            opts.selectedItemId = node.id;
            menuItem.trigger("mouseleave.sidemenu");
        }

        changeMenuSelect(menuItem);
    }
</script>