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
		String artid=request.getParameter("artid");
		String title=request.getParameter("title");
		String maincontent=request.getParameter("maincontent");
		String authorid=request.getParameter("authorid");
		String categoryid=request.getParameter("categoryid");
		String createtime=request.getParameter("createtime");
		String updatetime=request.getParameter("updatetime");
		String sql = "INSERT INTO [dbo].[Article]\r\n"
				+ "           ([ArticleID]\r\n"
				+ "           ,[Title]\r\n"
				+ "           ,[MainContent]\r\n"
				+ "           ,[AuthorID]\r\n"
				+ "           ,[CategoryID]\r\n"
				+ "           ,[CreateTime]\r\n"
				+ "           ,[UpdateTime])\r\n"
				+ "     VALUES(?,?,?,?,?,?,?)";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, artid);
				stmt.setString(2, title);
				stmt.setString(3, maincontent);
				stmt.setString(4, authorid);
				stmt.setString(5, categoryid);
				stmt.setString(6, createtime);
				stmt.setString(7, updatetime);
						
				
			int updateCount = stmt.executeUpdate();
			if(updateCount<1) {
				
				request.setAttribute("message", "新增失敗");
			}else {
				
				request.setAttribute("message", "新增成功");
			}		
			
			stmt.close();
			request.getRequestDispatcher("/Article/CreateArt.jsp")
			.forward(request, response);
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
//			request.setAttribute("message", "新增失敗");
//            request.getRequestDispatcher("/Article/CreateArt.jsp")
//			.forward(request, response);
			
		} catch (NamingException e) {
			e.printStackTrace();
//			request.setAttribute("message", "新增失敗");
//            request.getRequestDispatcher("/Article/CreateArt.jsp")
//			.forward(request, response);
		}
		finally {
			
		        if (conn != null) {
		            try {
						conn.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
		 
		        }
		}	
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
