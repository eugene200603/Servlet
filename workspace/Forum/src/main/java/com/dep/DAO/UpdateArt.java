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


@WebServlet("/UpdateArt")
public class UpdateArt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String artid=request.getParameter("updateId");
		String title=request.getParameter("updateTitle");
		String maincontent=request.getParameter("updateMaincontent");
		String authorid=request.getParameter("updateAuthorid");
		String categoryid=request.getParameter("updateCategoryid");
		String createtime=request.getParameter("updateCreatetime");
		String updatetime=request.getParameter("updateUpdatetime");
		String sql = "UPDATE [dbo].[Article]"				
				+ "SET   [Title]=? \r\n"
				+ "      ,[MainContent]=?\r\n"
				+ "      ,[AuthorID]=?\r\n"
				+ "      ,[CategoryID]=?\r\n"
				+ "      ,[CreateTime]=?\r\n"
				+ "      ,[UpdateTime]=?\r\n"
				+ " WHERE ArticleID=?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				
				stmt.setString(1,title);
				stmt.setString(2, maincontent);
				stmt.setString(3,authorid);
				stmt.setString(4,categoryid);
				stmt.setString(5,createtime);
				stmt.setString(6,updatetime);
				stmt.setString(7,artid);
				
				
				int updateCount = stmt.executeUpdate();
				if(updateCount>=1) {
					
					request.setAttribute("message", "修改成功");
				}else {
					
					request.setAttribute("message", "修改失敗");
				}		
				
			stmt.close();
			request.getRequestDispatcher("/Article/UpdateArt.jsp")
			.forward(request, response);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "修改失敗");
			request.getRequestDispatcher("/Article/UpdateArt.jsp")
			.forward(request, response);
		} catch (NamingException e) {
			e.printStackTrace();
			
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
