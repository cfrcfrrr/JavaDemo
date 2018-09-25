<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%--浏览器显示还是在此页面，但内容显示的是forward指向的页面，另外此页面中其他定义都不会显示--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_action_forward</title>
</head>
<body>
    <p>forward to hello_world.jsp</p>
    <jsp:forward page="hello_world.jsp"/>
    <p>end.</p>
</body>
</html>
