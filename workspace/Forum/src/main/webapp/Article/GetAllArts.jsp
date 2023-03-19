<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.dep.*" %>
	<%! @SuppressWarnings("unchecked") %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>文章資料</title>
			</head>

			<body >
				<div >
					
					<table>
						<tr>
							<th>縮圖</th>
							<th>文章ID</th>
							<th>標題</th>
							<th>文章內容</th>
							<th>發文者帳號</th>
							<th>類別</th>
							<th>發文日期</th>
							<th>修改日期</th>
							<th>狀態</th>
							<th>更新</th>
							<th>刪除</th>
							<c:forEach items="${arts}" var="art" varStatus="s">
						<tr>
							<td>N</td>
							<td>${art.artid}
							<td>${art.title}
							<td>${art.maincontent}
							<td>${art.authorid}
							<td>${art.categoryname}
							<td>${art.createtime}
							<td>${art.updatetime}
							<td>N</td>
							<td>
								<form action="../GetArtForU?artid=${art.artid}" method="post"
									>
									<button type="submit" id="update">Update</button>
								</form>

							<td>
								<form action="../DeleteArt?artid=${art.artid}" method="post"
									onsubmit="return confirm('是否確定刪除？')">
									<button type="submit">Delete</button>
								</form>
								</c:forEach>
					</table>

				</div>
			</body>

			</html>