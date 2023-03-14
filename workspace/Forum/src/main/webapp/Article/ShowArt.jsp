<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>文章 資料</h2>
<jsp:useBean id="t" scope="request" class="com.dep.bean.ArticleBean" />

<form method="post" action="./UpdateArt">
<table>
<tr><td>文章ID
	<td><input type="text" readonly name="updateId" value="${art.artid}">
	<tr><td>標題<td><input type="text" name="updateTitle" value="${art.title}">
	<tr><td>文章內容<td><input type="text" name="updateMaincontent" value="${art.maincontent}">
	<tr><td>發文者ID<td><input type="text" name="updateAuthorid" value="${art.authorid}">
	<tr><td>類別ID<td><input type="text" name="updateCategoryid" value="${art.categoryid}">
	<tr><td>發文日期<td><input type="text" name="updateCreatetime" value="${art.createtime}">
	<tr><td>修改日期<td><input type="text" name="updateUpdatetime" value="${art.updatetime}">	
</table>

<input type="submit" value="送出" />
</form>
</div>
</body>
</html>