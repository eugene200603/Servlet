<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章資料</title>

<style>
form {
	width: 500px;
	margin: 0 auto;
}

form h2 {
	font-size: 24px;
	margin-bottom: 20px;
}

form input[type="text"], form textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	border: none;
	box-shadow: inset 0px 0px 2px rgba(0, 0, 0, 0.5);
}

form select {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	border: none;
	box-shadow: inset 0px 0px 2px rgba(0, 0, 0, 0.5);
}

form input[type="file"] {
	margin-bottom: 10px;
}

form input[type="submit"] {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s;
}

form input[type="submit"]:hover {
	background-color: #0062cc;
}
</style>
</head>
<body>
	<h2>文章 資料</h2>
	<jsp:useBean id="art" scope="request" class="com.dep.bean.ArticleBean" />

	<form method="post" action="./UpdateArt"
		onsubmit="return confirm('是否確定變更？')" enctype="multipart/form-data">
		<input type="hidden" name="artid" value="${art.artid}" /> <input
			type="hidden" name="updateCreatetime" value="${art.createtime}" /> <input
			type="hidden" name="updateUpdatetime" value="${art.updatetime}" />
		使用者帳號 : <input type="text" name="updateAuthorid"
			value="${art.authorid}" />
		<p>
			標題 : <input type="text" name="updateTitle" value="${art.title}" />
		<p>
			文章內容 :
			<textarea name="updateMaincontent">${art.maincontent}</textarea>
		<p>
			類別ID : <select name="updateCategoryid">
				<option value="1">公告</option>
				<option value="2">心情抒發</option>
				<option value="3">好康情報</option>
				<option value="4">其他</option>
			</select>
		<p>
			狀態 : <select name="updatestate">
				<option value="正常">正常</option>
				<option value="待審核">待審核</option>
				<option value="隱蔽">隱蔽</option>
			</select> 圖片:<input type="file" name="updateimg">
		<p>
			<input type="submit" value="確定" />
	</form>


</body>
</html>