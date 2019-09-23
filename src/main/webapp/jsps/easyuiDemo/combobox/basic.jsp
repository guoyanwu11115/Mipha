<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath(); %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Basic ComboBox - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/component/easyui/themes/default/easyui.css">
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <%--<script type="text/javascript" src="<%=path%>/component/easyui/jquery.min.js"></script>--%>
    <script type="text/javascript" src="<%=path%>/component/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#country").combobox({
                onSelect:function () {
                    var value = $("#country").combobox('getValue');
                    var text = $("#country").combobox('getText');
                    alert(text+":"+value);
                }
                ,
                onChange:function () {
                    var value = $("#country").combobox('getValue');
                    var text = $("#country").combobox('getText');
                    alert(text+":"+value);
                }
            });
            //绑定blur事件
            $("#country").combobox('textbox').bind('blur',function (e) {
                var value = $("#country").combobox('getValue');
                var text = $("#country").combobox('getText');
                alert("blur|"+text+":"+value);
            });
        });
    </script>
</head>
<body>
<h2>Basic ComboBox</h2>
<p>Type in ComboBox to try auto complete.</p>
<%--<div style="margin:20px 0"></div>--%>
<%--<select class="easyui-combobox" name="state" label="State:" labelPosition="top" style="width:120px;">--%>

<input class="easyui-combobox" id="" data-options="
		valueField: 'label',
		textField: 'value',
		data: [{
			label: 'java',
			value: 'Java'
		},{
			label: 'perl',
			value: 'Perl'
		},{
			label: 'ruby',
			value: 'Ruby'
		}]" />
<select class="easyui-combobox" id="country"  style="width:150px;height: 30px">
    <option value="AL">Alabama</option>
    <option value="AK">Alaska</option>
    <option value="AZ">Arizona</option>
    <option value="AR">Arkansas</option>
    <option value="CA">California</option>
    <option value="CO">Colorado</option>
    <option value="CT">Connecticut</option>
    <option value="DE">Delaware</option>
    <option value="FL">Florida</option>
    <option value="GA">Georgia</option>
    <option value="HI">Hawaii</option>
    <option value="ID">Idaho</option>
    <option value="IL">Illinois</option>
    <option value="IN">Indiana</option>
    <option value="IA">Iowa</option>
    <option value="KS">Kansas</option>
    <option value="KY">Kentucky</option>
    <option value="LA">Louisiana</option>
    <option value="ME">Maine</option>
    <option value="MD">Maryland</option>
    <option value="MA">Massachusetts</option>
    <option value="MI">Michigan</option>
    <option value="MN">Minnesota</option>
    <option value="MS">Mississippi</option>
    <option value="MO">Missouri</option>
    <option value="MT">Montana</option>
    <option value="NE">Nebraska</option>
    <option value="NV">Nevada</option>
    <option value="NH">New Hampshire</option>
    <option value="NJ">New Jersey</option>
    <option value="NM">New Mexico</option>
    <option value="NY">New York</option>
    <option value="NC">North Carolina</option>
    <option value="ND">North Dakota</option>
    <option value="OH" selected>Ohio</option>
    <option value="OK">Oklahoma</option>
    <option value="OR">Oregon</option>
    <option value="PA">Pennsylvania</option>
    <option value="RI">Rhode Island</option>
    <option value="SC">South Carolina</option>
    <option value="SD">South Dakota</option>
    <option value="TN">Tennessee</option>
    <option value="TX">Texas</option>
    <option value="UT">Utah</option>
    <option value="VT">Vermont</option>
    <option value="VA">Virginia</option>
    <option value="WA">Washington</option>
    <option value="WV">West Virginia</option>
    <option value="WI">Wisconsin</option>
    <option value="WY">Wyoming</option>
</select>
<%--<div class="easyui-panel" style="width:100%;max-width:400px;padding:30px 60px;">--%>
    <%--<div style="margin-bottom:20px">--%>
    <%--</div>--%>
<%--</div>--%>
</body>
</html>