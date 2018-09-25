<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%--第三步：从上往下执行页面，但<%!...%>内的内容不会执行--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_life_cycle</title>
</head>
<body>
    <%!
        public void jspInit(){
            initVar++;
            System.out.println("jspInit(): JSP被初始化了" + initVar + "次了");
        }
        public void jspDestroy(){
            destroyVar++;
            System.out.println("jspDestroy(): JSP被销毁了" + destroyVar + "次");
        }
        public void myMethod(){
            System.out.println("myMethod()");
        }
    %>
    <%!
        private int initVar = 0;
        private int serviceVar = 0;
        private int destroyVar = 0;
    %>
    <%
        serviceVar++;
        System.out.println("_jspService(): JSP共响应了" + serviceVar + "次请求");
        String content1 = "初始化次数：" + initVar;
        String content2 = "响应客户请求次数：" + serviceVar;
        String content3 = "销毁次数：" + destroyVar;
    %>
    <p><%=content1%></p>
    <p><%=content2%></p>
    <p><%=content3%></p>
</body>
</html>
