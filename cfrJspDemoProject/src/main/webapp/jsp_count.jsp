<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/29
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_count</title>
</head>
<body>
<%
    Integer hitsCount = (Integer) application.getAttribute("hitCounter");
    if (hitsCount == null || hitsCount == 0) {
        out.println("首次访问");
        hitsCount = 1;
    } else {
        out.println("再次访问");
        hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
%>
<p>访问次数：<%=hitsCount%></p>
</body>
</html>
