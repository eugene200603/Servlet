<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>文章 資料</h2>
<jsp:useBean id="art" scope="request" class="com.dep.bean.ArticleBean" />
<table>
<tr><td>文章ID
	<td><input type="text" disabled value="${art.artid}">
	<tr><td>標題<td><input type="text" disabled value="${art.title}">
	<tr><td>文章內容<td><input type="text" disabled value="${art.maincontent}">
	<tr><td>發文者ID<td><input type="text" disabled value="${art.authorid}">
	<tr><td>類別ID<td><input type="text" disabled value="${art.categoryid}">
	<tr><td>發文日期<td><input type="text" disabled value="${art.createtime}">
	<tr><td>修改日期<td><input type="text" disabled value="${art.updatetime}">
</table>


</div>
</body>
</html>