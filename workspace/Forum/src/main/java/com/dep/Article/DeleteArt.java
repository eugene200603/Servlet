package com.dep.Article;

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

import com.dep.DAO.ArticleDAO;
import com.dep.bean.*;



@WebServlet("/DeleteArt")
public class DeleteArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String artid=request.getParameter("artid");
		
		ArticleDAO dao=new ArticleDAO();
		boolean success=dao.deleteArt(artid);
			if(success) {				
				request.setAttribute("message", "刪除成功");
			}else {
				
				request.setAttribute("message", "刪除失敗");
			}		
			request.getRequestDispatcher("/Article/DeleteArt.jsp")
			.forward(request, response);
			
	
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
