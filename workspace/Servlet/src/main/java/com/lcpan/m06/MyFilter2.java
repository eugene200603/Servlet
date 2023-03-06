package com.lcpan.m06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;


@WebFilter("/FilterDemo")
public class MyFilter2 extends HttpFilter implements Filter {
  
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		out.write("Pre-processing of MyFilter2<br>");
		chain.doFilter(request, response);
		out.write("Post-processing of MyFilter2<br>");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
