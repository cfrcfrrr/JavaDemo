<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/29
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_redirect</title>
</head>
<body>
    <%
        String site = new String("http://www.baidu.com");
//        方法一：
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
//        方法二：
//        response.sendRedirect(site);
    %>
</body>
</html>
