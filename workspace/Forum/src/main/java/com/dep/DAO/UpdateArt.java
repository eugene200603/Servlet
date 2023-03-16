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
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String artid=request.getParameter("updateId");
		String title=request.getParameter("updateTitle");
		String maincontent=request.getParameter("updateMaincontent");
		String authorid=request.getParameter("updateAuthorid");
		String categoryid=request.getParameter("updateCategoryid");
		String createtime=request.getParameter("updateCreatetime");
		String updatetime=request.getParameter("updateUpdatetime");
		
			ArticleBean art=new ArticleBean();
			art.setArtid(artid);
			art.setTitle(title);
			art.setMaincontent(maincontent);
			art.setAuthorid(authorid);
			art.setCategoryid(categoryid);
			art.setCreatetime(createtime);
			art.setUpdatetime(updatetime);
		
				
			ArticleDAO dao=new ArticleDAO();
			boolean success=dao.updateArticle(art)	;
				
				if(success) {
					
					request.setAttribute("message", "修改成功");
				}else {
					
					request.setAttribute("message", "修改失敗");
				}					
			request.getRequestDispatcher("/Article/UpdateArt.jsp")
			.forward(request, response);		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
