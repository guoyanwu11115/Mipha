<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/27
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        // var obj = {
        //     1:"value1",
        //     "2":"value2",
        //     count:3,
        //     person:[{id:1,name:"zhang3"},{id:2,name:"li4"}],
        //     object: {id:1,msg:"json对象里的obj"}
        // };
        //json对象
        var a = {
            "1":"value1",
            "2":"value2",
            "count":3,
            "person":[{"id":1,"name":"zhang3"},{"id":2,"name":"li4"}],
            "object": {"id":1,"msg":"json对象里的obj"}
        };
        //json字符串
        var b= '{"1":"value1","2":"value2","count":3,"person":[{"id":1,"name":"zhang3"},{"id":2,"name":"li4"}],"object": {"id":1,"msg":"json对象里的obj"}}';

        $(document).ready(function () {
            var jsonText =  $("#jsonText").val();
            var obj = $.parseJSON(jsonText);   //json字符串转json对象
            $("#readJson").click(function () {
                alert("jsonText:"+jsonText);
                alert(obj["1"]);
                alert(obj["2"]);
                alert(obj.count);
                alert(obj.person[1].id);
                alert(obj.object.msg);
            });
            //添加
            $("#addObjToJson").click(function () {
                alert("开始添加sex数据到json");
                add(obj);
                alert(obj.sex);
            });
            //修改
            $("#modifyJson").click(function () {
                alert("开始修改json数据");
                modify(obj);
                alert(obj.count);
            });
            //删除
            $("#deleteJson").click(function () {
                alert("开始删除json数据count值");
                del(obj);
                alert(obj.count);
            });
            //1.jquery 转换方式
            $("#str2jsonJquery").click(function () {
                console.info("nihao");
                alert("b转换json前type:"+typeof (b));
                var b2json = $.parseJSON(b);
                alert(b2json);
                alert("b转换json后type:"+typeof (b2json));
                alert(b2json.person[1].name);

            });
            //2.Browser支持的转换方式
            //json2str
            $("#json2str").click(function () {
                alert("a转换str前type:"+typeof (a));
                alert(a.person[0].name);
                var a2str = JSON.stringify(a);
                alert(a2str);
                alert("a转换str后type:"+typeof (a2str));
            });
            //str2json
            $("#str2json").click(function () {
                alert("b转换json前type:"+typeof (b));
                var b2json = JSON.parse(b);
                alert(b2json);
                alert("b转换json后type:"+typeof (b2json));
                alert(b2json.person[1].name);
            });
            /**
             * 3.javascript支持的方式：
             * eval('('+jsonStr+')');
             *注：将json字符串用'('+jsonStr+')' 小括号括起来
             */
            $("#str2jsonJavaScript").click(function () {
                alert("b转换json前type:"+typeof (b));
                var b2json = eval('('+b+')');
                alert(b2json);
                alert("b转换json后type:"+typeof (b2json));
                alert(b2json.person[1].name);
            });
            //二 json对象与java对象来相互转换
            $("#java2json").click(function () {
                var type = document.getElementById("java2json").value;
                alert(type);
                $.ajax({
                    url:"studyJson.html",
                    data:{type:type},
                    type:"post",
                    dataType:"json",
                    success:function (data) {
                        $.each(data,function (i,n) {
                            if('string'!=typeof n){
                                alert("第"+i+"个对象:"+n.loginName);
                            }
                        })

                        $("#jsonText3").html(data);
                    },
                    error:function () {

                    }

                });
            });

        });
        function add(obj) {
            obj.sex="man";
        }
        function modify(obj){
            obj.count=5;
        }
        function del(obj) {
            delete obj.count;
        }
    </script>
</head>
<body>
<p>${name}</p>
<p>${password}</p>
<fieldset>
    <legend>Json</legend>
    <table>
        <tr>
            <td>
                <p>Json对象及其操作</p>
                <textarea id="jsonText" rows="10" cols="90" >
                    {
                    "1":"value1",
                    "2":"value2",
                    "count":3,
                    "person":[{"id":1,"name":"zhang3"},{"id":2,"name":"li4"}],
                    "object": {"id":1,"msg":"json对象里的obj"}
                    }
                    </textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" id="readJson" value="readJson">
                <input type="button" id="addObjToJson" value="addObjToJson">
                <input type="button" id="modifyJson" value="modifyJson">
                <input type="button" id="deleteJson" value="deleteJson">
            </td>
        </tr>
        <!-- -->
        <tr>
            <td>
                <p>一.Json对象与Json字符串的转化、JSON字符串与Java对象的转换 </p>
                <textarea id="jsonText2" rows="15" cols="120" >
                    //json对象
                    var a = {
                            "1":"value1",
                            "2":"value2",
                            "count":3,
                            "person":[{"id":1,"name":"zhang3"},{"id":2,"name":"li4"}],
                            "object": {"id":1,"msg":"json对象里的obj"}
                        };
                    //json字符串
                    var b= '{"1":"value1","2":"value2","count":3,"person":[{"id":1,"name":"zhang3"},
                        {"id":2,"name":"li4"}],"object": {"id":1,"msg":"json对象里的obj"}
                        }';
                    </textarea>
            </td>
        </tr>
        <tr>
            <td>1.jQuery插件支持的转换方式：$.parseJSON( jsonstr );</td>
        </tr>
        <tr>
            <td>
                <input type="button" id="str2jsonJquery" value="json2str">
            </td>
        </tr>
        <tr>
            <td>2.Browser转换方式:JSON.stringify(obj)/JSON.parse(string)</td>
        </tr>
        <tr>
            <td>
                <input type="button" id="json2str" value="json2str">
                <input type="button" id="str2json" value="str2json">
            </td>
        </tr>
        <tr>
            <td>3.javascript支持的转换方式：eval('('+jsonStr+')');<p style="color: red">注:将json字符串用'('+jsonStr+')' 小括号括起来</p></td>
        </tr>
        <tr>
            <td>
                <input type="button" id="str2jsonJavaScript" value="json2str">
            </td>
        </tr>
        <!-- -->
        <tr>
            <td>
                <p>二.JSON字符串与Java对象的转换 </p>
                <textarea id="jsonText3" rows="8" cols="50">

                    </textarea>
            </td>
        </tr>
        <tr>
            <td>1. 把java 对象列表转换为json对象数组，并转为字符串</td>
        </tr>
        <tr>
            <td>
                <input type="button" id="java2json" value="java2json">
            </td>
        </tr>
    </table>
</fieldset>
</body>
</html>
