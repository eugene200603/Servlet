package com.dep.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.dep.bean.ArticleBean;



@WebServlet("/GetAllArts")
public class GetAllArts extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ArticleDAO dao=new ArticleDAO();
		List<ArticleBean>arts=dao.findAllArticle();
			request.setAttribute("arts", arts);	
			request.getRequestDispatcher("/Article/GetAllArts.jsp")
			.forward(request, response);
	}		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		doGet(request, response);
	}

}
