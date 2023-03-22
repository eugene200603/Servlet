幫我美化CSS,,標題置中,input的label靠左文字美化
input的輸入框置中右邊不要緊貼form div,左右margin, padding平均
button左右並排中間間隔，"返回"button改成紅色系
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改文章資料</title>
<style>
body {
  background-color: #f0f0f0;
}

form {
  width: 500px;
  margin: 0 auto;
  background-color: #fff;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

form h2 {
  font-size: 24px;
  margin-bottom: 20px;
  text-align: center;
}

form input[type="text"],
form textarea,
form select {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border: none;
  box-shadow: inset 0px 0px 2px rgba(0, 0, 0, 0.5);
  color: #333;
  font-size: 16px;
  text-align: center;
}

form input[type="file"] {
  margin-bottom: 10px;
}

form input[type="submit"],
input[type="button"] {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 20px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

form input[type="submit"]:hover,
input[type="button"]:hover {
  background-color: #0062cc;
}

form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  font-size: 16px;
  text-align: left;
}

form div {
  margin-bottom: 10px;
}

form #preview {
  max-width: 100%;
  max-height: 200px;
  display: block;
  margin: 10px auto;
}

.form {
  background-color: #f0f0f0;
  padding: 20px;
}

</style>
<script>
	function previewImage() {
		let preview = document.querySelector('#preview');
		let file = document.querySelector('input[type=file]').files[0];
		let reader = new FileReader();

		reader.addEventListener('load', function() {
			preview.src = reader.result;
			document.getElementById('preview').style.display = 'block';
		}, false);

		if (file) {
			reader.readAsDataURL(file);
		}
	}
	function goBack() {
		window.history.go(-1);
	}
</script>

</head>
<body>


	<jsp:useBean id="art" scope="request" class="com.dep.bean.ArticleBean" />
	<div class="form">
		<form method="post" action="./UpdateArt"
			onsubmit="return confirm('是否確定變更？')" enctype="multipart/form-data">
			<h2>修改文章資料</h2>
			<input type="hidden" name="artid" value="${art.artid}" /> <input
				type="hidden" name="updateCreatetime" value="${art.createtime}" />
			<input type="hidden" name="updateUpdatetime"
				value="${art.updatetime}" />
			<div>
				使用者帳號 : <input type="text" name="updateAuthorid"
					value="${art.authorid}" />
			</div>
			<div>
				標題 : <input type="text" name="updateTitle" value="${art.title}" />
			</div>
			文章內容 :
			<textarea name="updateMaincontent">${art.maincontent}</textarea>
			<div>
				類別ID : <select name="updateCategoryid">
					<option value="1">公告</option>
					<option value="2">心情抒發</option>
					<option value="3">好康情報</option>
					<option value="4">其他</option>
				</select>
			</div>
			<div>
				狀態 : <select name="updatestate">
					<option value="正常">正常</option>
					<option value="待審核">待審核</option>
					<option value="隱蔽">隱蔽</option>
				</select>
			</div>
			<div>
				圖片:<input type="file" name="updateimg" onchange="previewImage()">
				<img id="preview" src="#" alt="Preview"
					style="max-width: 150px; max-height: 150px; display: none;">
			</div>
			<div class="button">
				<input type="submit" value="確定" /> <input type="button" value="返回"
					onclick="goBack()" />
			</div>
		</form>

	</div>




</body>

</html>

