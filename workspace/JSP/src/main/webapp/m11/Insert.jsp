<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>新增員工資料</h2>
<jsp:useBean id="emp" scope="request" class="com.lcpan.bean.EmpBean" />

	<%=request.getAttribute("message") %>
</div>
</body>
</html>