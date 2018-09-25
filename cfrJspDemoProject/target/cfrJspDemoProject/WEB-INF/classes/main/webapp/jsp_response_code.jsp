<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_response_code</title>
</head>
<body>
    <%
        response.sendError(407, "Need authentication!!!" );
    %>
</body>
</html>
