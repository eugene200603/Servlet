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
<th>文章ID<th>標題<th>文章內容<th>發文者ID <th>類別ID <th>發文日期 <th>修改日期 <th>更新 <th>刪除
<c:forEach items="${arts}" var="art" varStatus="s">
	<tr><td>${art.artid}
	<td>${art.title}
	<td>${art.maincontent}
	<td>${art.authorid}
	<td>${art.categoryid}
	<td>${art.createtime}
	<td>${art.updatetime}	
	<td><form action="./GetArtForU?artid=${art.artid}" method="post">             
        <button type="submit">Update</button>
      </form>
	
	<td><form action="./DeleteArt?artid=${art.artid}" method="post">             
        <button type="submit">Delete</button>
      </form>
	</c:forEach>	
</table>

</div>
</body>
</html>