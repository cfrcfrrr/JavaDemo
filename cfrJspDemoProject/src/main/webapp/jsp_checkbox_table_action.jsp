<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp_checkbox_table_action</title>
</head>
<body>
    <table width="100%" border="1" align="center">
        <tr bgcolor="#949494">
            <th>参数名</th>
            <th>参数值</th>
        </tr>
        <%
            Enumeration paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String paramName = (String) paramNames.nextElement();
                out.print("<tr><td>" + paramName + "</td>\n");
                String paramValue = request.getParameter(paramName);
                out.println("<td>" + paramValue + "</td></tr>\n");
            }
        %>
    </table>
</body>
</html>
