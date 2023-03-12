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

import com.dep.bean.TopicBean;



@WebServlet("/GetAllTopics")
public class GetAllTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String sql ="SELECT * From Topic";
				
		Context context;
		try {
			context = new InitialContext();
			DataSource ds=(DataSource)context
				.lookup("java:/comp/env/jdbc/Department");
			conn=ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);			
			ResultSet rs = stmt.executeQuery();
			List<TopicBean>topics=new ArrayList<>();
			TopicBean topic=null;
			
			while(rs.next()) {
				topic=new TopicBean();
				topic.setTopicid(rs.getString("topicid"));
				topic.setTitle(rs.getString("title"));
				topic.setMaincontent(rs.getString("maincontent"));
				topic.setCreatetime(rs.getString("createtime"));
				topic.setSort(rs.getString("sort"));
				topic.setAuthorid(rs.getString("authorid"));
				topic.setLikecount(rs.getString("likecount"));
				topics.add(topic);
			}
			request.setAttribute("topics", topics);
			stmt.close();
			request.getRequestDispatcher("/Topic/GetAllTopics.jsp")
			.forward(request, response);
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		doGet(request, response);
	}

}
