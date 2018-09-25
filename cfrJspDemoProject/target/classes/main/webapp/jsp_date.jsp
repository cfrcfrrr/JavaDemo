<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_date</title>
</head>
<body>
    <%
        Date date = new Date();
        out.print("<h2 align=\"center\">" + date.toString() + "</h2>");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        out.print("<h2 align=\"center\">" + sdf.format(date) + "</h2>");
    %>
</body>
</html>
