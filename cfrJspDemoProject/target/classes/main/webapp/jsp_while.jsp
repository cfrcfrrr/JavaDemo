<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_while</title>
</head>
<body>
    <%!
        int index = 0;
    %>
    <%
        while(index++ < 3) {
    %>
        <font color="green" size="<%=index%>">
            你好，世界！
        </font>
    <%
        }
    %>
</body>
</html>
