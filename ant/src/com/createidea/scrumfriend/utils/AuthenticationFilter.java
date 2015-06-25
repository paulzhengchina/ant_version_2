package com.createidea.scrumfriend.utils;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.createidea.scrumfriend.to.UserTo;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.support.StaticApplicationContext;

import com.createidea.scrumfriend.struts.action.BaseAction;
import com.createidea.scrumfriend.to.UserTo;

public class AuthenticationFilter implements Filter {
    private static final String DEFAULT_PAGE="/";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Object user;
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		String currentUrlString=request.getRequestURI();
		HttpSession session=request.getSession();
		if(session==null ){
			response.sendRedirect(request.getContextPath()+DEFAULT_PAGE);
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
