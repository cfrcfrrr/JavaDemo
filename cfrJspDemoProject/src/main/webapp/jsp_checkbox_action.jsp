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
    <title>jsp_checkbox_action</title>
</head>
<body>
    <ul>
        <li>
            <p>
                <b>Google 是否选中：</b>
                <%=request.getParameter("google")%>
            </p>
        </li>
        <li>
            <p>
                <b>菜鸟教程是否选中：</b>
                <%=request.getParameter("baidu")%>
            </p>
        </li>
        <li>
            <p>
                <b>淘宝是否选中：</b>
                <%=request.getParameter("taobao")%>
            </p>
        </li>
    </ul>
</body>
</html>
