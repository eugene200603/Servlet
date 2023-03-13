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

import com.dep.bean.TopicBean;
import com.lcpan.bean.EmpBean;


@WebServlet("/GetTopicForU")
public class GetTopicForU extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String topicid=request.getParameter("topicid");
		String sql = "SELECT [topicid]\r\n"
				+ "      ,[title]\r\n"
				+ "      ,[maincontent]\r\n"
				+ "      ,[createtime]\r\n"
				+ "      ,[sort]\r\n"
				+ "      ,[authorid]\r\n"
				+ "      ,[likecount]\r\n"
				+ "  FROM [dbo].[Topic] where topicid=?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, topicid);
				ResultSet rs = stmt.executeQuery();
				TopicBean top=new TopicBean();
			
			if(rs.next()) {
				top.setTopicid(rs.getString("topicid"));
				top.setTitle(rs.getString("title"));
				top.setMaincontent(rs.getString("maincontent"));
				top.setCreatetime(rs.getString("createtime"));
				top.setSort(rs.getString("sort"));
				top.setTitle(rs.getString("title"));
				top.setLikecount(rs.getString("likecount"));
			}
			request.setAttribute("top", top);
			stmt.close();
			request.getRequestDispatcher("/Topic/ShowTopic.jsp")
			.forward(request, response);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
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
