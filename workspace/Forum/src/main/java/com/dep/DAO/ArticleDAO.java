package com.dep.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import com.dep.bean.ArticleBean;

public class ArticleDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connection conn() throws NamingException, SQLException {
		String Sourse="java:/comp/env/jdbc/Department";
		Context context = new InitialContext();
		DataSource ds=(DataSource)context
			.lookup(Sourse);
		 return ds.getConnection();
		
		
	}
	
	// C
		
		
	

		// R
		
		public List<ArticleBean> findAllArticle() {			
			String sql ="SELECT * From Article";
			
			Connection conn = null;
			try {
				conn=conn();
				
				PreparedStatement stmt = conn.prepareStatement(sql);			
				ResultSet rs = stmt.executeQuery();
				List<ArticleBean>arts=new ArrayList<>();
				ArticleBean art=null;
				
				while(rs.next()) {
					art=new ArticleBean();
					art.setArtid(rs.getString("articleid"));
					art.setTitle(rs.getString("title"));
					art.setMaincontent(rs.getString("maincontent"));
					art.setAuthorid(rs.getString("authorid"));
					art.setCategoryid(rs.getString("categoryid"));
					art.setCreatetime(rs.getString("createtime"));
					art.setUpdatetime(rs.getString("updatetime"));
					arts.add(art);
				}
				return arts;
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
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
			return null;
		}
		
		
		
		// U
		
		
		
		
		// D
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
