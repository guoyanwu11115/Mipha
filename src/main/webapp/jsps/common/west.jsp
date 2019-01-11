<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/29
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>west</title>
</head>
<body>
<div class="easyui-sidemenu" data-options="data:data"></div>
<script type="text/javascript">
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
