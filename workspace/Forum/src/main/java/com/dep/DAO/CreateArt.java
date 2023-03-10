package com.dep.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.dep.bean.*;


@WebServlet("/CreateArt")
public class CreateArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		String artid=request.getParameter("artid");
		String title=request.getParameter("title");
		String maincontent=request.getParameter("maincontent");
		String authorid=request.getParameter("authorid");
		String categoryid=request.getParameter("categoryid");
		String createtime=request.getParameter("createtime");
		String updatetime=request.getParameter("updatetime");	
		
		ArticleBean art=new ArticleBean();
			art.setArtid(artid);
			art.setTitle(title);
			art.setMaincontent(maincontent);
			art.setAuthorid(authorid);
			art.setCategoryid(categoryid);
			art.setCreatetime(createtime);
			art.setUpdatetime(updatetime);
			
		ArticleDAO dao=new ArticleDAO();
			boolean success=dao.CreateArt(art);
				
			if(success) {
				
				request.setAttribute("message", "新增成功");
			}else {
				
				request.setAttribute("message", "新增失敗");
			}		
			request.getRequestDispatcher("/Article/CreateArt.jsp")
			.forward(request, response);
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
