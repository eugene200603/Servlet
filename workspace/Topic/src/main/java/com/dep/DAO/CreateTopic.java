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
		String empno=request.getParameter("empno");
		String ename=request.getParameter("ename");
		String hiredate=request.getParameter("hiredate");
		String salary=request.getParameter("salary");
		String deptno=request.getParameter("deptno");
		String title=request.getParameter("title");
		String sql = "INSERT INTO [dbo].[employee]\r\n"
				+ "           ([empno]\r\n"
				+ "           ,[ename]\r\n"
				+ "           ,[hiredate]\r\n"
				+ "           ,[salary]\r\n"
				+ "           ,[deptno]\r\n"
				+ "           ,[title])\r\n"
				+ "     VALUES(?,?,?,?,?,?)";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/servdb");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, empno);
				stmt.setString(2, ename);
				stmt.setString(3, hiredate);
				stmt.setString(4, salary);
				stmt.setString(5, deptno);
				stmt.setString(6, title);
						
			int updateCount = stmt.executeUpdate();
			if(updateCount<1) {
				
				request.setAttribute("message", "新增失敗");
			}else {
				
				request.setAttribute("message", "新增成功");
			}		
			
			stmt.close();
			request.getRequestDispatcher("/m11/Insert.jsp")
			.forward(request, response);
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("message", "新增失敗");
            request.getRequestDispatcher("/m11/Insert.jsp")
			.forward(request, response);
			
		} catch (NamingException e) {
			
			request.setAttribute("message", "新增失敗");
            request.getRequestDispatcher("/m11/Insert.jsp")
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
