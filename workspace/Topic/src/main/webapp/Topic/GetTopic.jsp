<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topic</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>文章 資料</h2>
<jsp:useBean id="topic" scope="request" class="com.dep.bean.TopicBean" />
<table>
<tr><td>文章編號
	<td><input type="text" disabled value="${topic.topicid}">
	<tr><td>標題<td><input type="text" disabled value="${topic.title}">
	<tr><td>內文<td><input type="text" disabled value="${topic.maincontent}">
	<tr><td>發佈時間<td><input type="text" disabled value="${topic.createtime}">
	<tr><td>分類<td><input type="text" disabled value="${topic.sort}">
	<tr><td>發文者<td><input type="text" disabled value="${topic.authorid}">
	<tr><td>被點讚次數<td><input type="text" disabled value="${topic.likecount}">
	
	
</table>
</div>
</body>
</html>