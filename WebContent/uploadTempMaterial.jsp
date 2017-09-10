<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>上传临时素材</title>
</head>
<body>
	<form method="post" action="/WeiXin_QiYe_Demo/uploadTempMaterialServlet" enctype="multipart/form-data">
		选择一个文件:
		<input type="file" name="uploadFile" />
		<br/><br/>
		<input type="submit" value="上传" />
	</form>
</body>
</html>