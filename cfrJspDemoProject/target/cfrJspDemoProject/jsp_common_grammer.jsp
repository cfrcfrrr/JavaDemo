<%--jsp三个指令标签--%>
<%--“<%@ page ... %>”定义页面的依赖属性，如脚本语言、error页面、缓存需求等等--%>
<%--“<%@ include ... %>”包含其他页面--%>
<%--“<%@ taglib  ... %>”引入标签库的定义，可以是自定义标签--%>
<%@ page import="javafx.scene.shape.Circle" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>jsp_grammer</title>
</head>
<body>
    <%--这部分注释不会被发送至浏览器，也不会被编译--%>
    <!--这部分注释会被发送至浏览器，通过查看网页源代码可以看到-->
    <\% <%--这样会显示“<%”--%>
    %\> <%--这样会显示“%\>”--%>
    <%!
        Circle a = new Circle(2.0);
    %>

    <%
        out.println("你的IP地址是 " + request.getRemoteAddr());
    %>
    <p>
        <%=a%>
    </p>
    <%@ include file="hello_world.jsp"%>
    <p>
        当前时间：<%=new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new java.util.Date())%>
    </p>
</body>
</html>
