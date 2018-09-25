<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_action_use_bean</title>
</head>
<body>
    <%!
        int id = 1;
        String myName = "aaa";
    %>
    <jsp:useBean id="jspActionUseBeanA" class="main.jspdemo.JspActionUseBean"/>
    <jsp:setProperty name="jspActionUseBeanA" property="id" value="1"/>
    <jsp:setProperty name="jspActionUseBeanA" property="name" value="aaa"/>
    <%--<jsp:setProperty name="jspActionUseBeanA" property="birthday" value="Sun Aug 26 19:59:08 CST 2018"/>--%>
    <p>
        id is: <jsp:getProperty name="jspActionUseBeanA" property="id"/>
    </p>
    <p>
        name is: <jsp:getProperty name="jspActionUseBeanA" property="name"/>
    </p>
    <p>
        birthday is: <jsp:getProperty name="jspActionUseBeanA" property="birthday"/>
    </p>
</body>
</html>
