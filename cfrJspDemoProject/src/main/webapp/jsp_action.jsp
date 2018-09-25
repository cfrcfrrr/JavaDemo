<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_action</title>
</head>
<body>
    <%!
        private int initVar = 0;
        private int destroyVar = 0;
        public void jspInit(){
            System.out.println("jsp_action  init " + ++initVar);
        }
        public void jspDestroy(){
            System.out.println("jsp_action destroy " + ++destroyVar);
        }
    %>
    <%--<p></p>--%>
    <%@ include file="jsp_action_include.jsp"%>
    <%--<p></p>--%>
    <%--jsp:inculde，执行到此步时才跳转到包含页面去编译执行该文件--%>
    <%--<jsp:include page="jsp_action_include.jsp" flush="true"/>--%>
</body>
</html>
