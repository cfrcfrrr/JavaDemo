<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_cookie_delete</title>
</head>
<body>
    <%
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if(cookies != null) {
            out.println("<h2>查找Cookie名与值</h2>");
            for(int i = 0; i < cookies.length; i ++) {
                cookie = cookies[i];
                if((cookie.getName()).compareTo("name") == 0){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.print("删除Cookie: " + cookie.getName() + "<br/>");
                }
                out.print("参数名：" + cookie.getName());
                out.print("<br>");
                out.print("参数值：" + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
                out.print("-----<br>");
            }
        } else {
                out.println("<h2>没有发现Cookie</h2>");
        }
    %>
</body>
</html>
