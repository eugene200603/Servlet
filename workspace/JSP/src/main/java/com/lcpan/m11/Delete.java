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


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		String empno=request.getParameter("empno");
		String sql = "DELETE FROM [dbo].[employee]\r\n"
				+ "      WHERE empno=?";
		
		try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context
						.lookup("java:/comp/env/jdbc/servdb");
				conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, empno);
				
			
//				ResultSet rs = stmt.executeQuery();
//				EmpBean emp=new EmpBean();
//			
//			if(rs.next()) {
//				emp.setEmpno(rs.getString("empno"));				
//			}
//			request.setAttribute("emp", emp);
			
			int updateCount = stmt.executeUpdate();
			if(updateCount>=1) {
				
				request.setAttribute("message", "刪除成功");
			}else {
				
				request.setAttribute("message", "刪除失敗");
			}		
			stmt.close();
			request.getRequestDispatcher("/m11/Delete.jsp")
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
