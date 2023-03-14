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

import com.dep.bean.ArticleBean;
import com.lcpan.bean.EmpBean;


@WebServlet("/GetArtForU")
public class GetArtForU extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String artid=request.getParameter("artid");
		String sql = "select* from Article WHERE ArticleID=?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/Department");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, artid);
				ResultSet rs = stmt.executeQuery();
				ArticleBean art=new ArticleBean();
			
			if(rs.next()) {
				art.setArtid(rs.getString("articleid"));
				art.setTitle(rs.getString("title"));
				art.setMaincontent(rs.getString("maincontent"));
				art.setAuthorid(rs.getString("authorid"));
				art.setCategoryid(rs.getString("categoryid"));
				art.setCreatetime(rs.getString("createtime"));
				art.setUpdatetime(rs.getString("updatetime"));
			}
			request.setAttribute("art", art);
			stmt.close();
			request.getRequestDispatcher("/Article/ShowArt.jsp")
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
