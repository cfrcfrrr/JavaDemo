<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String str = URLEncoder.encode(request.getParameter("name"), "utf-8");
    Cookie name = new Cookie("name", str);
    Cookie url = new Cookie("url", request.getParameter("url"));
//    设置cookie过期时间
    name.setMaxAge(60*24*24);
    url.setMaxAge(60*60*24);
//    在响应头部添加cookie
    response.addCookie(name);
    response.addCookie(url);
%>
<html>
<head>
    <title>jsp_cookie_action</title>
</head>
<body>
    <ul>
        <li>
            <p>
                <b>网站名：</b>
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
