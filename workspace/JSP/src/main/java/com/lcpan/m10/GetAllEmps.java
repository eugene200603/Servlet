package com.lcpan.m10;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcpan.bean.EmpBean;


@WebServlet("/GetAllEmps")
public class GetAllEmps extends HttpServlet {
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
		String url="jdbc:sqlserver://localhost:1433;databasename=jdbc;encrypt=false";
	    String username="banana";
	    String pwd="1234";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			conn=DriverManager.getConnection(url,username,pwd);
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
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}
