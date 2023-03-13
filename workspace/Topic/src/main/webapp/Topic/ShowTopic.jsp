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
<jsp:useBean id="top" scope="request" class="com.dep.bean.TopicBean" />

<form method="post" action="./UpdateTopic">
<table>
<tr><td>文章編號
	<td><input type="text" readonly name="updateId" value="<%=top.getTopicid()%>">
	<tr><td>標題<td><input type="text" name="updateTitle" value="<%=top.getTitle()%>">
	<tr><td>內文<td><input type="text" name="updateMaincontent" value="<%=top.getMaincontent()%>">
	<tr><td>發佈時間<td><input type="text" name="updateCreatetime" value="<%=top.getCreatetime()%>">
	<tr><td>分類<td><input type="text" name="updateSort" value="<%=top.getSort()%>">
	<tr><td>發文者<td><input type="text" name="updateAuthorid" value="<%=top.getAuthorid()%>">
	<tr><td>被點讚次數<td><input type="text" name="updateLikecount" value="<%=top.getLikecount()%>">	
</table>

<input type="submit" value="送出" />
</form>
</div>
</body>
</html>