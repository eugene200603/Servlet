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

import com.lcpan.bean.EmpBean;


@WebServlet("/CreateTopic")
public class CreateTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String topicid=request.getParameter("topicid");
		String title=request.getParameter("title");
		String maincontent=request.getParameter("maincontent");
		String createtime=request.getParameter("createtime");
		String sort=request.getParameter("sort");
		String authorid=request.getParameter("authorid");
		String likecount=request.getParameter("likecount");
		String sql = "INSERT INTO [dbo].[Topic]\r\n"
				+ "           ([TopicID]\r\n"
				+ "           ,[Title]\r\n"
				+ "           ,[MainContent]\r\n"
				+ "           ,[CreateTime]\r\n"
				+ "           ,[Sort]\r\n"
				+ "           ,[AuthorID]\r\n"
				+ "           ,[LikeCount])\r\n"
				+ "     VALUES(?,?,?,?,?,?,?)";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, topicid);
				stmt.setString(2, title);
				stmt.setString(3, maincontent);
				stmt.setString(4, createtime);
				stmt.setString(5, sort);
				stmt.setString(6, authorid);
				stmt.setString(7, likecount);
						
				
			int updateCount = stmt.executeUpdate();
			if(updateCount<1) {
				
				request.setAttribute("message", "新增失敗");
			}else {
				
				request.setAttribute("message", "新增成功");
			}		
			
			stmt.close();
			request.getRequestDispatcher("/Topic/CreateTopic.jsp")
			.forward(request, response);
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("message", "新增失敗");
            request.getRequestDispatcher("/Topic/CreateTopic.jsp")
			.forward(request, response);
			
		} catch (NamingException e) {
			
			request.setAttribute("message", "新增失敗");
            request.getRequestDispatcher("/Topic/CreateTopic.jsp")
			.forward(request, response);
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
