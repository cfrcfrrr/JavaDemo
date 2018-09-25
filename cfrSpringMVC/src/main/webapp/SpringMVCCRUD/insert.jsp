<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="SpringMVCCRUD/insert.action">
		编号：<input type="text" name="mid" value=1><br> <!-- 视频中value值都是用字符串，是语法规范吗？ -->
		<!-- 视频中中文在服务端显示会有乱码（我这边没有乱码），因此需要在web.xml文件中添加一个过滤器 -->
		标题：<input type="text" name="title" value="三毛流浪记"><br>
		价格：<input type="text" name="price" value="11.1"><br>
		<!-- 日期类型如果直接提交会报400BadRequset，需要在控制层的父类上定义一个转换器 -->
		日期：<input type="text" name="pubdate" value="2018-05-16"><br> <!-- 遗留：日期能够用什么函数赋值吗？ -->
		类型：<input type="text" name="type.title" value="小说"><br>
		<input type="submit" value="提交"> <!-- 提交后相当于访问：http://localhost:8080/cfrWebJava/SpringMVCCRUD/insert.action?mid=1&title=三毛流浪记&price=11.1&pubdate=2018-05-16&type.title=小说 -->
		<input type="reset" value="重置">
	</form>
</body>
</html>