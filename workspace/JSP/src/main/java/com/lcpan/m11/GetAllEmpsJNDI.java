package com.lcpan.m11;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import com.lcpan.bean.EmpBean;


@WebServlet("/GetAllEmpsJNDI")
public class GetAllEmpsJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		String sql = "SELECT [empno]\r\n"
				+ "      ,[ename]\r\n"
				+ "      ,[hiredate]\r\n"
				+ "      ,[salary]\r\n"
				+ "      ,[deptno]\r\n"
				+ "      ,[title]\r\n"
				+ "  FROM [dbo].[employee]";
		
		
			Context context=new InitialContext();
			DataSource ds=(DataSource)context
					.lookup("java:/comp/env/jdbc/servdb");
		try (conn=ds.getConnection();){
			PreparedStatement stmt = conn.prepareStatement(sql);			
			ResultSet rs = stmt.executeQuery();
			List<EmpBean>emps=new ArrayList<>();
			EmpBean emp=null;
			
			while(rs.next()) {
				emp=new EmpBean();
				emp.setEmpno(rs.getString("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setHiredate(rs.getString("hiredate"));
				emp.setSalary(rs.getString("salary"));
				emp.setDeptno(rs.getString("deptno"));
				emp.setTitle(rs.getString("title"));	
				emps.add(emp);
			}
			request.setAttribute("emps", emps);
			stmt.close();
			request.getRequestDispatcher("/m10/GetAllEmps.jsp")
			.forward(request, response);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
