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
	<!-- 点击修改，相当于访问 http://localhost:8080/cfrWebJava/SpringMVCCRUD/update.action?mid=2&title=西游记=22.2&pubdate=2018-05-15&typeTitle=名著 -->
	<form action="SpringMVCCRUD/update.action">
		编号：<input type="text" name="mid" value=2><br>
		标题：<input type="text" name="title" value="西游记"><br>
		价格：<input type="text" name="price" value=22.2><br>
		日期：<input type="text" name="pubdate" value=2018-05-15><br>
		<!-- 不使用type.title的带.的方式，而是将Type的title重命名为为typeTitle，使得和message的title属性不重名，这样就可以不使用.方式。但是如果两个属性就是重名，结果会变成设置的两个值的组合 -->
		类型：<input type="text" name="typeTitle" value="名著"><br>
		<input type="submit" value="修改">
		<input type="reset" value="重置">
	</form>
</body>
</html>