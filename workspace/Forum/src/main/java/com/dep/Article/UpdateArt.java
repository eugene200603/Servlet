package com.dep.Article;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.dep.DAO.ArticleDAO;
import com.dep.bean.*;


@WebServlet("/UpdateArt")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
    )
public class UpdateArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		
		String title=request.getParameter("updateTitle");
		String maincontent=request.getParameter("updateMaincontent");
		String authorid=request.getParameter("updateAuthorid");
		String categoryid=request.getParameter("updateCategoryid");
		String createtime=request.getParameter("updateCreatetime");
		String updatetime=request.getParameter("updateUpdatetime");
		String updatestate=request.getParameter("updatestate");		
		Part img = request.getPart("updateimg");
		String artid=request.getParameter("artid");
		
		InputStream inputStream = img.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] bytes = outputStream.toByteArray();
		String base64Image = Base64.getEncoder().encodeToString(bytes);
	
		ArticleDAO dao=new ArticleDAO();
		ArticleBean art=new ArticleBean();
		
		if(bytes.length > 0) {
	        // 若有上傳新的圖片，則將新的圖片轉成 base64 字串，並更新至資料庫
	        
	        art.setImg(base64Image);
	       
		} else {
	        // 若沒有上傳新的圖片，則不更新資料庫的圖片欄位
	        ArticleBean oldArt = dao.findArticleByID(artid);
	        art.setImg(oldArt.getImg() != null ? oldArt.getImg() : "");
	        
		}
			
			art.setTitle(title);
			art.setMaincontent(maincontent);
			art.setAuthorid(authorid);
			art.setCategoryid(categoryid);
			art.setCreatetime(createtime);
			art.setUpdatetime(updatetime);
			art.setState(updatestate);;
			
			art.setArtid(artid);
				
			
			boolean success=dao.updateArticle(art)	;
				
				if(success) {
					
					request.setAttribute("message", "修改成功");
				}else {
					
					request.setAttribute("message", "修改失敗");
				}					
			request.getRequestDispatcher("/Article/UpdateArt.jsp")
			.forward(request, response);		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
