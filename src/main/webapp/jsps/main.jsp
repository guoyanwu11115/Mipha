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
        <script type="text/javascript" src="component/easyui/jquery.min.js"></script>
        <script type="text/javascript" src="component/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="component/easyui/locale/easyui-lang-zh_CN.js"></script>
        <%--<link rel="stylesheet" type="text/css" href="/demo.css">--%>
    </head>
    <body class="easyui-layout">
        <!-- 上部页面 -->
        <div data-options="region:'north',href:'jsps/common/north.jsp',border:false" style="height:60px;background:#99cdff;padding:10px">north region</div>
        <!-- 左部页面 -->
        <div data-options="region:'west',title:'系统菜单',data:data,onselect:onSelect" style="width:200px" id="sm" class="easyui-sidemenu"></div>
        <!-- 下部页面 -->
        <div data-options="region:'south',href:'jsps/common/south.jsp',border:false" style="height:30px;background:#8d8d8d;padding:10px;">south region</div>
        <!-- 中部页面 -->
        <div data-options="region:'center',href:'jsps/common/center.jsp',title:''"></div>
        <script type="text/javascript">
            var data = [{
                text: 'Item1',
                iconCls: 'icon-sum',
                state: 'open',
                children: [{
                    text: 'Option1'
                },{
                    text: 'Option2'
                },{
                    text: 'Option3',
                    children: [{
                        text: 'Option31'
                    },{
                        text: 'Option32'
                    }]
                }]
            },{
                text: 'Item2',
                iconCls: 'icon-more',
                children: [{
                    text: 'Option4'
                },{
                    text: 'Option5'
                },{
                    text: 'Option6'
                }]
            }];
            //打开标签页
            function onSelect(item){
                alert(11);
            }

            function toggle(){
                var opts = $('#sm').sidemenu('options');
                $('#sm').sidemenu(opts.collapsed ? 'expand' : 'collapse');
                opts = $('#sm').sidemenu('options');
                $('#sm').sidemenu('resize', {
                    width: opts.collapsed ? 60 : 200
                })
            }
        </script>
    </body>
</html>
