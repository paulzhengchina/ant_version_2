package com.createidea.scrumfriend.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.createidea.scrumfriend.dao.user.UserDao;
import com.createidea.scrumfriend.struts.action.UserAction;
import com.createidea.scrumfriend.to.UserTo;


public class UserTestCase extends TestCase {
     UserAction userAction=null;
     UserDao userDao=null;
     
 @Before
 public void setUp() throws Exception{
	 BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
	 userDao=(UserDao)factory.getBean("userDao");
	 userAction=(UserAction)factory.getBean("userAction");
	 userAction.setUser(new UserTo("admin","password"));
	 userDao.removeUser(new UserTo("test","password"));
	
 }
 @Test
 public  void testLoginSuccessful() throws Exception{
	 String result=userAction.verifyUser();
     Assert.assertEquals("success", result);					 
 }
 @Test
 public void testRegisterUserSuccess() throws Exception{
	 userAction.setUsername("test");
	 userAction.setPassword("password");
	 String result=userAction.registerUser();
	 Assert.assertEquals("success", result);
 }
 @Test
 public void testRegisterUserFailed() throws Exception{
	 UserTo user=userDao.getRandomUser();
	 userAction.setUsername(user.getName());
	 String result=userAction.registerUser();
	 Assert.assertEquals("input", result);
 }
 

}
