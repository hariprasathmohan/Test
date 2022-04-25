package com.bank.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionAvailFilter implements Filter {
	private ArrayList<String> excludedUrls = new ArrayList<>();
	public void destroy() {
	//System.out.println("Filter destroy");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpSession session=req.getSession();
		String path = ((HttpServletRequest) req).getServletPath();
		if(excludedUrls.contains(path)) {
			chain.doFilter(request, response);			
		} else {
			if(session.getAttribute("myAccount")!=null) {
				chain.doFilter(request, response);
			}
			else {
				PrintWriter out= response.getWriter();
				out.println("<script language='Javascript'>");
				out.println("alert('There is a problem    Login again to continue'); window.location.href ='login.html'");
				out.println("</script>");
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		//System.out.println("Filter Init");
		excludedUrls.add("/login.html");
		excludedUrls.add("/login");
		excludedUrls.add("/Register.jsp");
		excludedUrls.add("/register");
		excludedUrls.add("/branchList.jsp");
	}
}
