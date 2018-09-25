<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%--填写参数，点击提交后，相当于将参数传递给http://localhost:8080/jsp_form_get.jsp（或http://localhost:8080/jsp_form_post.jsp），
相当于访问http://localhost:8080/jsp_form_get.jsp?name=%E7%99%BE%E5%BA%A6&url=http://www.baidu.com（或http://localhost:8080/jsp_form_post.jsp?name=%E7%99%BE%E5%BA%A6&url=http://www.baidu.com）--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_form</title>
</head>
<body>
    <form action="jsp_form_post.jsp" method="get">
        站点名：<input type="text" name="name">
        <br />
        网址：<input type="text" name="url"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
