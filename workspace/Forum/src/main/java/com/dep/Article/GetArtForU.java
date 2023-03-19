package com.dep.Article;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
import com.dep.bean.ArticleBean;
import com.lcpan.bean.EmpBean;


@WebServlet("/GetArtForU")
public class GetArtForU extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String artid=request.getParameter("artid");
		ArticleDAO dao=new ArticleDAO();
		ArticleBean art=dao.findArticleByID(artid);
		
			request.setAttribute("art",art);
			
			request.getRequestDispatcher("/Article/ShowArt.jsp")
			.forward(request, response);
			
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
