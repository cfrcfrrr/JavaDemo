<%--遗留问题：但是这样是不是永远也只能提交一次？要是页面没跳转的话--%>
<%--遗留问题：而且前端不能防止刷新页面或回退页面再次操作的情况--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>form表单</title>
    <%--前端防止重复提交，方法一：设置是否已提交标识--%>
    <script type="text/javascript">
        var isCommitted = false;//表单是否已经提交标识，默认为false
        function dosubmit1(){
            if(isCommitted==false){
                isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
                return true;//返回true让表单正常提交
            }else{
                return false;//返回false那么表单将不提交
            }
        }
    </script>
    <%--前端防止重复提交，方法二：使提交按钮不可用--%>
    <script>
        function dosubmit2(){
            //获取表单提交按钮
            var btnSubmit = document.getElementById("submit2");
            //将表单提交按钮设置为不可用，这样就可以避免用户再次点击提交按钮
            btnSubmit.disabled= "disabled";
            //返回true让表单可以正常提交
            return true;
        }
    </script>
</head>
<body>
<%--onsubmit在提交表单时触发，常用于表单验证，当调用的函数返回false时表单就不会被提交，注意要有return，否则会被提交--%>
<form action="/servlet/DoFormServlet" method="post" onsubmit="return dosubmit1()">
    用户名1：<input type="text" name="username">
    <input type="submit" value="提交" id="submit1">
</form>

<form action="/servlet/DoFormServlet" method="post" onsubmit="return dosubmit2()">
    用户名2：<input type="text" name="username">
    <input type="submit" value="提交" id="submit2">
</form>

<form action="/servlet/DoFormServlet" method="post">
    <%--使用隐藏域存储生成的token--%>
    <input type="hidden" name="token" value="<%=session.getAttribute("token") %>">
    <%--使用EL表达式取出存储在session中的token--%>
    <%--<input type="hidden" name="token" value="${token}"/>--%>
    用户名3：<input type="text" name="username">
    <input type="submit" value="提交" id="submit3">
</form>

</body>
</html>