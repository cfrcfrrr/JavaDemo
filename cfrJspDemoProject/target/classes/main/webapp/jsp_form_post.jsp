<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.util.*"%>
<html>
<head>
    <title>jsp_form_post</title>
</head>
<body>
    <ul>
        <li>
            <p>
                <b>站点名：</b>
                <%
                    String name = new String((request.getParameter("name")).getBytes("ISO-8859-1"),"UTF-8");
                %>
                <%=name%>
            </p>
        </li>
        <li>
            <p>
                <b>网址：</b>
                <%=request.getParameter("url")%>
            </p>
        </li>
    </ul>
</body>
</html>
