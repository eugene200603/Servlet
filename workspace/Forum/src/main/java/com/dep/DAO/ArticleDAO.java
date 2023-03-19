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

import javax.sql.DataSource;

import com.dep.bean.ArticleBean;

public class ArticleDAO  {

	
	//Connection
	public Connection conn() throws NamingException, SQLException {
		String Sourse="java:/comp/env/jdbc/Department";
		Context context = new InitialContext();
		DataSource ds=(DataSource)context
			.lookup(Sourse);
		 return ds.getConnection();
		
		
	}
	
	// C
	public boolean  CreateArt(ArticleBean art) {
		
			String sql = "INSERT INTO [dbo].[Article]\r\n"
//					+ "           ([ArticleID]\r\n"
					+ "           ([Title]\r\n"
					+ "           ,[MainContent]\r\n"
					+ "           ,[AuthorID]\r\n"
					+ "           ,[CategoryID])\r\n"
//					+ "           ,[CreateTime]\r\n"
//					+ "           ,[UpdateTime])\r\n"
					+ "     VALUES(?,?,?,?)";
						
				try (Connection conn=conn();				
					PreparedStatement stmt = conn.prepareStatement(sql);){
//					stmt.setString(1,art.getArtid());
					stmt.setString(1,art.getTitle());
					stmt.setString(2,art.getMaincontent());
					stmt.setString(3,art.getAuthorid());
					stmt.setString(4,art.getCategoryid());
//					stmt.setString(6,art.getCreatetime());
//					stmt.setString(7,art.getUpdatetime());
					
				int updateCount = stmt.executeUpdate();
				if(updateCount>=1) {					
					return true;
				}else {					
					return false;
				}		
				
			} catch (SQLException e) {
				e.printStackTrace();

			} catch (NamingException e) {
				e.printStackTrace();
		
			}
			
			return false;	
	}
		
	
	
	
	
	//R-one
	public ArticleBean findArticleByID(String artid) {
		
			
			String sql = "SELECT * From Article where ArticleID=?";
			try (Connection conn=conn();
					PreparedStatement stmt = conn.prepareStatement(sql);){
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
				
				return art;
			} catch (SQLException e) {				
				e.printStackTrace();
			} catch (NamingException e) {				
				e.printStackTrace();
			}
			
		return null;
	}
	//findByCategory
	public List<ArticleBean> findArticleByCat(String categoryid) {	
		String sql = "SELECT * From Article \r\n"
				+ "INNER JOIN Category ON Article.CategoryID = Category.CategoryID \r\n"
				+ "where Article.CategoryID=?";
		try (Connection conn=conn();
				PreparedStatement stmt = conn.prepareStatement(sql);){
				stmt.setString(1, categoryid);
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
					art.setCategoryname(rs.getString("categoryname"));
					art.setCreatetime(rs.getString("createtime"));
					art.setUpdatetime(rs.getString("updatetime"));
					arts.add(art);
				}
				
				return arts;
		} catch (SQLException e) {				
			e.printStackTrace();
		} catch (NamingException e) {				
			e.printStackTrace();
		}
		
	return null;
}
	
	
	public List<ArticleBean> findArticleByMem(String authorid) {	
		String sql = "SELECT * From Article \r\n"
				+ "INNER JOIN Category ON Article.CategoryID = Category.CategoryID \r\n"
				+ "where Article.AuthorID=?";
		try (Connection conn=conn();
				PreparedStatement stmt = conn.prepareStatement(sql);){
				stmt.setString(1, authorid);
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
					art.setCategoryname(rs.getString("categoryname"));
					art.setCreatetime(rs.getString("createtime"));
					art.setUpdatetime(rs.getString("updatetime"));
					arts.add(art);
				}
				
				return arts;
		} catch (SQLException e) {				
			e.printStackTrace();
		} catch (NamingException e) {				
			e.printStackTrace();
		}
		
	return null;
}
		// R-ALL
		
		public List<ArticleBean> findAllArticle() {			
			String sql ="SELECT * From Article INNER JOIN Category ON Article.CategoryID = Category.CategoryID";
			
			try (Connection conn=conn();
				
				PreparedStatement stmt = conn.prepareStatement(sql);){			
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
					art.setCategoryname(rs.getString("categoryname"));
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
			return null;
		}
		
		
		
		// U
	public boolean updateArticle(ArticleBean art) {
		String sql = "UPDATE [dbo].[Article]"				
				+ "SET   [Title]=? \r\n"
				+ "      ,[MainContent]=?\r\n"
				+ "      ,[AuthorID]=?\r\n"
				+ "      ,[CategoryID]=?\r\n"
//				+ "      ,[CreateTime]=?\r\n"
//				+ "      ,[UpdateTime]=?\r\n"
				+ " WHERE ArticleID=?";
		
		try (Connection conn=conn();				
				PreparedStatement stmt = conn.prepareStatement(sql);){
				
				stmt.setString(1,art.getTitle());
				stmt.setString(2,art.getMaincontent());
				stmt.setString(3,art.getAuthorid());
				stmt.setString(4,art.getCategoryid());
//				stmt.setString(5,art.getCreatetime());
//				stmt.setString(6,art.getUpdatetime());
				stmt.setString(5,art.getArtid());
								
				int updateCount = stmt.executeUpdate();
				if(updateCount>=1) {					
					return true;
				}else {
					return false;				
				}		
	
		} catch (SQLException e) {
			e.printStackTrace();		
		} catch (NamingException e) {
			e.printStackTrace();			
		}
		return false;
	}	
		
		
		// D	
		public boolean  deleteArt(String artid){
				String sql = "DELETE FROM [dbo].[Article]\r\n"
						+ "      WHERE articleid=?";
				
				try (Connection conn=conn();
						PreparedStatement stmt = conn.prepareStatement(sql);){
						stmt.setString(1, artid);						
						
					int updateCount = stmt.executeUpdate();
					if(updateCount>=1) {						
						return true;
					}else {
						return false;						
					}							
				} catch (SQLException e) {					
					e.printStackTrace();
				} catch (NamingException e) {					
					e.printStackTrace();
				}
			
				return false;
		}
		
}
