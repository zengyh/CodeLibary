<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 15-8-3
  Time: 下午1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CheckBox.js demo</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/checkBox/CheckBox.js"></script>
</head>
<body>

<div id="userList">
    <input type="checkbox" value="全选" onclick="new CheckBoxs(userList).selectALL()"/>全选 <br>
    <input type="checkbox" tid="用户ID1" value="用户ID1"/>用户ID1
    <input type="checkbox" tid="用户ID2" value="用户ID2"/>用户ID2
    <input type="checkbox" tid="用户ID3" value="用户ID3"/>用户ID3<br>
    <input type="button" value="反选" onclick="new CheckBoxs(userList).inverseSelected()"/>
    <input type="button" value="重置" onclick="new CheckBoxs(userList).clearSelected()"/>
    <input type="button" value="点击按钮 tid='用户ID1' 的checkbox标志为选择状态 " onclick="new CheckBoxs(userList).select('用户ID1')"/>
</div>

</body>
</html>