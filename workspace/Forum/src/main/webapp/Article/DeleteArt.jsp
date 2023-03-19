<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>刪除員工資料</title>
  </head>

  <body style="background-color:#fdf5e6">
    <div align="center">
      <script>
        alert("<%=request.getAttribute("message") %>" );   
        location.href = "./Article/article.html";
      </script>
      
      <h2>刪除員工資料</h2>


    </div>
  </body>

  </html>