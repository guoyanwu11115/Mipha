<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>ServerPush-服务器推送技术</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            //Ajax短轮询
            setInterval(systemTime,1000);
            //Ajax长轮询
            longloop();
        });

        //服务器时间
        function systemTime() {
            $.ajax({
                url:"<%=path%>/serverPush/systemTime.html",
                data:{type:1},
                type:"get",
                success:function (data) {
                    $("#systemTime").html(data);
                },
                error:function () {
                    alert("error");
                }
            });
        }
        //实时新闻推送
        function longloop() {

            $.ajax({
                url:"<%=path%>/serverPush/realTimeNews.html",
                data:{type:1},
                type:"get",
                success:function (data) {
                    $("#news").html("【"+data+"】");
                    longloop();
                },
                error:function () {
                    $("#news").html("暂无新闻");
                }
            });
        }

    </script>
</head>
<body>
    <h2>ServerPush-服务器推送技术</h2>
    <table>
        <tr>
            <td>
                <ul>
                    <li>
                        <a href="javascript:">服务器时间</a>
                        <p id="systemTime"></p>
                    </li>
                    <li>
                        <a href="<%=path%>/jsps/java/realTimeNews.jsp"> Servlet异步-推送实时新闻</a>
                        <p id="news" style="color: red"></p>
                    </li>
                    <li>
                        <a href="">SSE-贵金属期货价格实时查询</a>
                        <div>
                            <h2 id="hint"></h2>
                        </div>
                        <div>
                            <div>
                                <fieldset>
                                    <legend>黄金：</legend>
                                    <p id="c0" style="color: #f00;"></p>
                                    <b><p id="s0">历史价格：</p></b>
                                </fieldset>

                            </div>

                            <div>
                                <fieldset>
                                    <legend>白银：</legend>
                                   <%-- <p>白银:</p>--%>
                                    <p id="c1" style="color: #f00;"></p>
                                    <b><p id="s1">历史价格：</p></b>
                                </fieldset>
                            </div>
                        </div>
                    </li>

                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <ul>
                    <%--<li><input type="button" id="charOfAt"  value="charAt(index)"/>:</li>
                    <li><input type="button" id="charOfCode"  value="charCode(index)"/>:</li>--%>
                </ul>
            </td>
        </tr>
        <tr>
            <td>
                <ul>
                    <li>

                    </li>
                    <li>

                    </li>
                    <li>

                    </li>
                </ul>
            </td>
        </tr>

    </table>

</body>
<script type="text/javascript">

    //贵金属期货价格实时查询
    function showPrice(index,data){
        $("#c"+index).html("当前价格："+data);
        var s = $("#s"+index).html();
        $("#s"+index).html(s+data+" ");
    }

    //检查当前浏览器是否支持SSE
    if(!!window.EventSource){
        var source = new EventSource('<%=path%>/serverPush/needPrice.html');
        source.onopen= function (evt) {
            console.log("Connecting server");
        };
        source.onmessage = function (evt) {
            var dataObj = evt.data;
            //alert(dataObj);
            var array = dataObj.split(",");
            $.each(array,function (i,n) {
                showPrice(i,n);
            })
            $("#hint").html("");
        };
        source.onerror = function (evt) { console.log("error") };
    }else{
        $("#hint").html("浏览器不支持SSE");
    }

</script>
</html>
