package com.dep.Article;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.GenericServlet;
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


@WebServlet("/CreateArt")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
    )
public class CreateArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		ArticleDAO dao=new ArticleDAO();
		String title=request.getParameter("title");
		String maincontent=request.getParameter("maincontent");
		String authorid=request.getParameter("authorid");
		String categoryid=request.getParameter("categoryid");
		String state=request.getParameter("state");
		Part img = request.getPart("img");
		
		String artid=request.getParameter("artid");
		String createtime=request.getParameter("createtime");
		String updatetime=request.getParameter("updatetime");	
		
		
		
		ArticleBean art=new ArticleBean();
			
			art.setTitle(title);
			art.setMaincontent(maincontent);
			art.setAuthorid(authorid);
			art.setCategoryid(categoryid);
			art.setState(state);
			art.setImg(img);
			/*art.setCreatetime(createtime);
			art.setUpdatetime(updatetime);
			art.setArtid(artid);*/
			
			
		
			boolean success=dao.CreateArt(art);
				
			if(success) {
				
				request.setAttribute("message", "新增成功");
			}else {
				
				request.setAttribute("message", "新增失敗");
			}		
			request.getRequestDispatcher("./Article/CreateArt.jsp")
			.forward(request, response);
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
