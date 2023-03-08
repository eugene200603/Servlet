package com.lcpan.m11;

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


@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String empno=request.getParameter("empno");
		String ename=request.getParameter("ename");
		String hiredate=request.getParameter("hiredate");
		String salary=request.getParameter("salary");
		String deptno=request.getParameter("deptno");
		String title=request.getParameter("title");
		String sql = "UPDATE [dbo].[employee]\r\n"
				+ "   SET [ename] =? \r\n"
				+ "      ,[hiredate]=? \r\n"
				+ "      ,[salary] =? \r\n"
				+ "      ,[deptno] =? \r\n"
				+ "      ,[title] =? \r\n"
				+ " WHERE [empno] =?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/servdb");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				
				stmt.setString(1, ename);
				stmt.setString(2, hiredate);
				stmt.setString(3, salary);
				stmt.setString(4, deptno);
				stmt.setString(5, title);
				stmt.setString(6, empno);
				
				int updateCount = stmt.executeUpdate();
				if(updateCount>=1) {
					
					request.setAttribute("message", "修改成功");
				}else {
					
					request.setAttribute("message", "修改失敗");
				}		
				
			stmt.close();
			request.getRequestDispatcher("/m11/Update.jsp")
			.forward(request, response);
			
			
		} catch (SQLException e) {
			
			request.setAttribute("message", "修改失敗");
			request.getRequestDispatcher("/m11/Update.jsp")
			.forward(request, response);
		} catch (NamingException e) {
			
			request.setAttribute("message", "修改失敗");
			request.getRequestDispatcher("/m11/Update.jsp")
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
