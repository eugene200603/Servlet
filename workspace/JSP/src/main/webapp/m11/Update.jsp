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
<h2>修改員工資料</h2>

<jsp:useBean id="emp" scope="request" class="com.lcpan.bean.EmpBean" />

<table>
<tr><td>員工編號
	<td><input type="text"  disabled value="<%=request.getParameter("updateNo")%>">
	<tr><td>姓名<td><input type="text" disabled value="<%=request.getParameter("updateName")%>">
	<tr><td>到職日<td><input type="text" disabled value="<%=request.getParameter("updateHiredate")%>">
	<tr><td>薪水<td><input type="text" disabled value="<%=request.getParameter("updateSalary")%>">
	<tr><td>部門編號<td><input type="text" disabled value="<%=request.getParameter("updateDeptno")%>">
	<tr><td>職稱<td><input type="text" disabled value="<%=request.getParameter("updateTitle")%>">
</table>
	<%=request.getAttribute("message") %>
</div>
</body>
</html>