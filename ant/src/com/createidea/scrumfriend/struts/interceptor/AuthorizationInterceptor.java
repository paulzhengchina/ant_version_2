package com.createidea.scrumfriend.struts.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorizationInterceptor extends AbstractInterceptor {
	private Logger logger=Logger.getRootLogger();
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,Object> sessionMap=invocation.getInvocationContext().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();		
		String path=request.getRequestURI();
		String userId=(String)sessionMap.get("CRNTUSER");
		boolean isLoginAction=path.contains("userLogin.action");
		boolean isResisterAction=path.contains("registerUser.action");
		logger.info(userId+path);
		if(isLoginAction||isResisterAction)
			return invocation.invoke();
		if(userId==null||"".equals(userId)){
			return Action.LOGIN;
		}		
		return invocation.invoke();			
	}

}
