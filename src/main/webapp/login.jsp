<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/26
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mipha登陆页</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" >
        $(document).ready(function () {
            var width = window.innerWidth;
            var heigth = window.innerHeight;
            // alert(width+""+heigth);
            $("#submit").click(function () {
                var name = $("#name").val();
                var password = $("#password").val();
                $.ajax({
                    url:"login.html",
                    data:{name:name,password:password},
                    type:"post",
                    dataType:"json",
                    success:function (data) {
                        //alert(data.code+"|"+data.msg);
                        if(0==data.code){
                            window.location.href="index.html";
                        }else{
                            $("#error_msg").text("用户名或密码错误!");
                            $("#name").focus();
                        }
                    },
                    error:function () {
                        var newWin = window.open("http://www.w3school.com","_blank","width="+width*0.1+",height="+heigth*0.1+",left="+width*0.45+",top="+heigth*0.45+"," +
                            "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes");
                        //newWin.document.write("<p>验证失败!</p>");
                        setTimeout(function () {
                            newWin.close()
                        },3000);
                    }
                });
            });
        });

    </script>
</head>

<body>
    <div>
        <fieldset>
            <legend>login</legend>
            <table>
                <tr>
                    <td>用户名:</td>
                    <td>
                        <input type="text" name="name" id="name">
                    </td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td>
                        <input type="text" name="password" id="password">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <h5 id="error_msg" style="color: red"></h5>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" id="submit" value="提交" >
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <fieldset>
        <legend>光标锁定用户名</legend>
        <a href="#name">点击输入用户名</a>
    </fieldset>
</body>

</html>
