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


@WebServlet("/UpdateTopic")
public class UpdateTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String topicid=request.getParameter("updateId");
		String title=request.getParameter("updateTitle");
		String maincontent=request.getParameter("updateMaincontent");
		String createtime=request.getParameter("updateCreatetime");
		String sort=request.getParameter("updateSort");
		String authorid=request.getParameter("updateAuthorid");
		String likecount=request.getParameter("updateLikecount");
		String sql = "UPDATE [dbo].[Topic]\r\n"
				+ "   SET [title] =? \r\n"
				+ "      ,[maincontent]=? \r\n"
				+ "      ,[createtime] =? \r\n"
				+ "      ,[sort] =? \r\n"
				+ "      ,[authorid] =? \r\n"
				+ "      ,[likecount] =? \r\n"
				+ " WHERE [topicid] =?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				
				stmt.setString(1,title);
				stmt.setString(2, maincontent);
				stmt.setString(3, createtime);
				stmt.setString(4,sort);
				stmt.setString(5, authorid);
				stmt.setString(6, likecount);
				stmt.setString(7, topicid);
				
				
				int updateCount = stmt.executeUpdate();
				if(updateCount>=1) {
					
					request.setAttribute("message", "修改成功");
				}else {
					
					request.setAttribute("message", "修改失敗");
				}		
				
			stmt.close();
			request.getRequestDispatcher("/Topic/UpdateTopic.jsp")
			.forward(request, response);
			
			
		} catch (SQLException e) {
			request.setAttribute("message", "修改失敗");
			request.getRequestDispatcher("/Topic/UpdateTopic.jsp")
			.forward(request, response);
		} catch (NamingException e) {
			
			
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
