<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"     %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PC端网页授权</title>

<script src="/js/jquery.js"></script>
<script src="http://rescdn.qqmail.com/node/ww/wwopenmng/js/sso/wwLogin-1.0.0.js"></script>
</head>
<body>

<%
         String code= request.getParameter("code");
         String state=request.getParameter("state");
%>
hello，这里是第三方应用
code=<%= code%>
state=<%= state%>

</body>
</html>