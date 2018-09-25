<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%--用
http://localhost:8080/jsp_request_param?name=%E7%99%BE%E5%BA%A6&url=http://www.baidu.com
访问--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_form_get</title>
</head>
<body>
<ul>
    <li>
        <p>
            <b>站点名：</b>
            <%=request.getParameter("name")%>
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
