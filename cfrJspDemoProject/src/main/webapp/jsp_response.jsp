<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_response</title>
</head>
<body>
    <%
        response.setIntHeader("Refresh", 5);
        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        if(calendar.get(Calendar.AM_PM) == 0){
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        String CT = hour + ":" + minute + ":" + second + " " + am_pm;
        out.println("当前时间：" + CT + "\n");
    %>
</body>
</html>
