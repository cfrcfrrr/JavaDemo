<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_checkbox</title>
</head>
<body>
    <form action="jsp_checkbox_action.jsp" method="post" target="_blank">
        <input type="checkbox" name="google" checked="checked"/>Google
        <input type="checkbox" name="baidu"/>百度
        <input type="checkbox" name="taobao" checked="checked"/>淘宝
        <input type="submit" value="选择网站"/>
    </form>
</body>
</html>
