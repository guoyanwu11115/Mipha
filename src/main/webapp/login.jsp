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
    <style>.error{color:red;}</style>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- 登录验证 -->
    <script type="text/javascript" src="js/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" >
        $(document).ready(function () {
            <!-- 启动验证validate -->
            validateRule();
        });

        $.validator.setDefaults({
            submitHandler:function () {
                login();
            }
        });

        function login() {
            var width = window.innerWidth;
            var heigth = window.innerHeight;
            var name = $("#name").val();
            var password = $("#password").val();
            $.ajax({
                url:"login.html",
                data:{name:name,password:password},
                type:"post",
                dataType:"json",
                success:function (data) {
                    alert(data.code+"|"+data.msg);
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
        }
        //校验规则
        function validateRule() {
            $("#loginForm").validate({
                rules:{
                    name:{
                        required:true,
                        minlength:2
                    },
                    password:{
                        required:true
                    }
                },
                messages:{
                    name:{
                        required:"name is required.",
                        minlength:"Please enter at least 2 characters."
                    },
                    password:{
                        required:"password is required."
                    }
                }
            });
        }

    </script>
</head>

<body>
    <div>
        <form id="loginForm" method="post" action="">
            <fieldset>
                <legend>login</legend>
                <table>
                    <tr>
                        <td><label for="name">用户名:</label></td>
                        <td>
                            <input type="text" id="name" name="name">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="password">密码:</label></td>
                        <td>
                            <input type="text" id="password" name="password">
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
                            <input type="submit" name="submit" value="登陆">
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
    <%--<fieldset>
        <legend>光标锁定用户名</legend>
        <a href="#name">点击输入用户名</a>
    </fieldset>--%>
</body>

</html>
