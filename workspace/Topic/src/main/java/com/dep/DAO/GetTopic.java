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


@WebServlet("/GetTopic")
public class GetTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String topicid=request.getParameter("topicid");
		String sql = "SELECT [TopicID]\n"
				+ "      ,[Title]\n"
				+ "      ,[MainContent]\n"
				+ "      ,[CreateTime]\n"
				+ "      ,[Sort]\n"
				+ "      ,[AuthorID]\n"
				+ "      ,[LikeCount]\n"
				+ "  FROM [dbo].[Topic] where topicid=?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, topicid);
				ResultSet rs = stmt.executeQuery();
				TopicBean topic=new TopicBean();
			
			if(rs.next()) {
				topic.setTopicid(rs.getString("topicid"));
				topic.setTitle(rs.getString("title"));
				topic.setMaincontent(rs.getString("maincontent"));
				topic.setCreatetime(rs.getString("createtime"));
				topic.setSort(rs.getString("sort"));
				topic.setAuthorid(rs.getString("authorid"));
				topic.setLikecount(rs.getString("likecount"));
			}
			request.setAttribute("topic", topic);
			stmt.close();
			request.getRequestDispatcher("/Topic/GetTopic.jsp")
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
