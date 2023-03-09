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
<h2>員工 資料</h2>
<jsp:useBean id="emp" scope="request" class="com.lcpan.bean.EmpBean" />

<form method="post" action="./Update">
<table>
<tr><td>員工編號
	<td><input type="text" readonly name="updateNo" value="<%=emp.getEmpno()%>">
	<tr><td>姓名<td><input type="text" name="updateName" value="<%=emp.getEname()%>">
	<tr><td>到職日<td><input type="text" name="updateHiredate" value="<%=emp.getHiredate()%>">
	<tr><td>薪水<td><input type="text" name="updateSalary" value="<%=emp.getSalary()%>">
	<tr><td>部門編號<td><input type="text" name="updateDeptno" value="<%=emp.getDeptno()%>">
	<tr><td>職稱<td><input type="text" name="updateTitle" value="<%=emp.getTitle()%>">	
</table>

<input type="submit" value="送出" />
</form>
</div>
</body>
</html>