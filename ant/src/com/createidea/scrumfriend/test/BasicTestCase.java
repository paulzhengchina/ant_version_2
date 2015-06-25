package com.createidea.scrumfriend.test;

import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.UserTo;
import com.opensymphony.xwork2.ActionProxy;

public class BasicTestCase extends StrutsSpringTestCase {
    protected BeanFactory factory;
    protected ActionProxy actionProxy;	
    protected ActionMapping actionMapping;
    protected UserTo currentUser;
    protected final static String USER="CRNTUSER";
	@Override
	protected String[] getContextLocations() {
		// TODO Auto-generated method stub
		String[] locations= new String[1];
		locations[0]="/applicationContext-*.xml";
	    return locations;
	}
	@Override
	protected void setupBeforeInitDispatcher() throws Exception {
		// TODO Auto-generated method stub
		factory=new ClassPathXmlApplicationContext("applicationContext-*.xml");
		currentUser=prepareCurrentUser();
		super.setupBeforeInitDispatcher();
	}
	
    private UserTo prepareCurrentUser(){
	   UserService userService=(UserService)factory.getBean("userService");
	   return userService.getUserByName("admin");
    }

       
}
