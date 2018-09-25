<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_action_include</title>
</head>
<body>
    <%!
        private int includeInitVar = 0;
        private int includeServiceVar = 0;
        private int includeDestroyVar = 0;
        public void jspInit() {
            System.out.println("jsp_action_include init " + ++includeInitVar);
        }
        public void jspDestroy(){
            System.out.println("jsp_action_include destroy " + ++includeDestroyVar);
        }
    %>
    <%
        includeServiceVar++;
        out.println("Hello jsp world1 print to browser. " + includeServiceVar);
        System.out.println("Hello jsp world print to console. " + includeServiceVar);
    %>

</body>
</html>
