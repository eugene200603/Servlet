<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article</title>
<style>
body {
  font-family: Arial, sans-serif;
  background-color: #F2F3F4;
  color: #2C3E50;
}

h2 {
  text-align: center;
}

table {
  border-collapse: separate;
  border-spacing: 0;
  border: 4px solid #34495E;
  width: 20%; 
  margin: 30px auto;
  background-color: #feffed;
}

td {
  text-align: center;
  padding: 10px;
  border: 1px solid #34495E;
}

th {
  background-color: #ebf0ff;
  color: white;
  padding: 10px;
  border: 1px solid #34495E;
}

img {
  max-width: 150px;
  max-height: 150px;
}

tr:nth-child(even) {
  background-color: #ebf0ff;
}

tr:hover {
  background-color: #ffe5c7;
}

button {
  background-color: #34495E;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
}

button:hover {
  background-color: #2C3E50;
}
</style>
</head>
<body>
<div align="center">
<h2>文章詳細資料</h2>

<jsp:useBean id="art" scope="request" class="com.dep.bean.ArticleBean" />
<table>
	<tr><td>文章ID</td><td>${art.artid}</td></tr>
	<tr><td>標題</td><td>${art.title}</td></tr>
	<tr><td>文章內容<td>${art.maincontent}</td></tr>
	<tr><td>發文者帳號<td>${art.authorid}</td></tr>
	<tr><td>類別<td>${art.categoryid}</td></tr>
	<tr><td>發文日期<td>${art.createtime}</td></tr>
	<tr><td>修改日期<td>${art.updatetime}</td></tr>
	<tr><td>狀態<td>${art.state}</td></tr>
	<tr><td>圖片<td><img  src="data:image/png;base64,${art.img}" alt="圖片" style="max-width: 150px; max-height: 150px; "></td></tr>
</table>
		<button onclick="history.back()">返回</button>

</div>
</body>
</html>