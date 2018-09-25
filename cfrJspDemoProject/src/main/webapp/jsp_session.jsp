<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date createTime = new Date(session.getCreationTime());
    Date lastAccessTime = new Date(session.getLastAccessedTime());
    String title = "再次访问菜鸟教程示例";
    Integer visitCount = 0;
    String visitCountKey = "visitCount";
    String userIDKey = "userID";
    String userID = "ABCD";
    // 估计因为默认打开了index.jsp界面，导致访问本界面时不走isNew()分支，导致获取属性时报空异常。
//    if (session.isNew()) {
//        title = "访问菜鸟教程示例";
//        session.setAttribute(userIDKey, userID);
//        session.setAttribute(visitCountKey, visitCount);
//    } else {
    visitCount = (Integer) session.getAttribute(visitCountKey);
    if (visitCount == null) {
        visitCount = 0;
    }
    visitCount += 1;
    userID = (String) session.getAttribute(userIDKey);
    session.setAttribute(visitCountKey, visitCount);
    session.setAttribute(userIDKey, userID);
//    }
%>
<html>
<head>
    <title>jsp_session</title>
</head>
<body>
<table border="1" align="center">
    <tr bgcolor="#949494">
        <th>Session 信息</th>
        <th>值</th>
    </tr>
    <tr>
        <td>id</td>
        <td><% out.print(session.getId()); %></td>
    </tr>
    <tr>
        <td>创建时间</td>
        <td><% out.print(createTime); %></td>
    </tr>
    <tr>
        <td>最后访问时间</td>
        <td><% out.print(lastAccessTime); %></td>
    </tr>
    <tr>
        <td>用户 ID</td>
        <td><% out.print(userID); %></td>
    </tr>
    <tr>
        <td>访问次数</td>
        <td><% out.print(visitCount); %></td>
    </tr>
</table>
</body>
</html>
