<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/21
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath(); %>
<html>
<head>
    <title>realTimeNews</title>
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript">

        function longloop() {
            $.get("<%=path%>/pushServer/realTimeNews.html",function (data) {
                $("#news").html(data);
                longloop();
            });
        }
        longloop();
    </script>
</head>
<body>
<p>实时新闻推送</p>
<p id="news"></p>
</body>
</html>
