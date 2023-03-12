<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.dep.*"%>
    <%! @SuppressWarnings("unchecked") %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章資料</title>
</head>
<body style="background-color:#fdf5e6">
<div align="center">
<h2>文 章 資 料</h2>
<table border="1">
<tr style="background-color:#a8fefa">
<th>文章代號<th>標題<th>文章內容<th>文章發表時間 <th>文章類別 <th>發文者代號 <th>被點讚次數 
<c:forEach items="${topics}" var="topic" varStatus="s">
	<tr><td>${topic.topicid}
	<td>${topic.title}
	<td>${topic.maincontent}
	<td>${topic.createtime}
	<td>${topic.sort}
	<td>${topic.authorid}
	<td>${topic.likecount}		
	</c:forEach>	
</table>

</div>
</body>
</html>