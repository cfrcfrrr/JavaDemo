<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_switch_case</title>
</head>
<body>
    <%!
        int day = 3;
    %>
    <%
        switch (day) {
            case 0:
                out.println("星期天");
                break;
            case 1:
                out.println("星期一");
                break;
            case 2:
                out.println("星期二");
                break;
            case 3:
                out.println("星期三");
                break;
            case 4:
                out.println("星期四");
                break;
            case 5:
                out.println("星期五");
                break;
            case 6:
                out.println("星期六");
                break;
            default:
                out.println("unknown day");
        }
    %>
</body>
</html>
