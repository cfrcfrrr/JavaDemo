<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%--查看网页源代码显示为
<html><head>
    <title>jsp_action_dynamic_xml_element</title>
    <meta charset="utf-8">
</head>
<body>
    <dynamicxmlelement dynamicxmlelement="属性值">
            XML元素主体
        </dynamicxmlelement>
</body></html>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>jsp_action_dynamic_xml_element</title>
    <meta charset="utf-8"/>
</head>
<body>
    <jsp:element name="dynamicXmlElement">
        <jsp:attribute name="dynamicXmlElement">
            属性值
        </jsp:attribute>
        <jsp:body>
            XML元素主体
        </jsp:body>
    </jsp:element>
</body>
</html>
