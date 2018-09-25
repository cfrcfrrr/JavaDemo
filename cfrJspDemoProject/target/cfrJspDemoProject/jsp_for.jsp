<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_for</title>
</head>
<body>
<%!
    int index;
%>
<%
    for (index = 1; index <= 3; index++) {
%>
    <font color="green" size="<%=index%>">
        你好，世界！
    </font>
<%
    }
%>
</body>
</html>
