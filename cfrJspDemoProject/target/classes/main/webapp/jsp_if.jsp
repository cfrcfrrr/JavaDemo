<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_if</title>
</head>
<body>
    <%!
        int day = 3;
    %>
    <% if (day == 1 | day == 7) { %>
        <p>今天是周末</p>
    <% } else { %>
        <p>今天不是周末</p>
    <% } %>

    <%
        day = 1;
        if (day == 1 | day == 7) {
            out.println("今天是周末");
        } else {
            out.println("今天不是周末");
        }
    %>
</body>
</html>
