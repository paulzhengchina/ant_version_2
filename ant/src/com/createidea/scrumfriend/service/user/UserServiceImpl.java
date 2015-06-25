package com.createidea.scrumfriend.service.user;

import java.util.List;
import java.util.Random;

import com.createidea.scrumfriend.dao.user.UserDao;
import com.createidea.scrumfriend.to.UserTo;
import com.createidea.scrumfriend.utils.email.SendMailService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private SendMailService sendEmailService;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserTo getUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(name);
	}

	@Override
	public String registerUser(String email) {
		// TODO Auto-generated method stub
		UserTo user=userDao.getUserByEmail(email);
		if(user!=null)
		  return "email_existed";
		else{
			String password=generateRandomPassword()+"";
			userDao.createUser(null,password,email);
			String message="感谢您注册小蚂蚁看板，您的初始密码为："+password;
			sendEmailService.sender(email, message,"小蚂蚁看板注册信息");
			return "success";
		}
	}

	@Override
	public boolean hasDefaultProject(UserTo user) {
		// TODO Auto-generated method stub
		user=userDao.getUserByEmail(user.getEmail());
		if(user.getDefaultProject()!=null)
			return true;
		else
			return false;
	}

	@Override
	public UserTo getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	@Override
	public void setDefaultProject(String userId, String projectId) {
		userDao.setDefaultProject(userId,projectId);
	}

	@Override
	public UserTo getUserByEmail(String email) {
		// TODO Auto-generated method stub
		if(email!=null&&!("".equals(email)))
		  return userDao.getUserByEmail(email);
		else 
		  return null;
	}
    
    private int generateRandomPassword(){
    	int password=(int)(Math.random()*1000000);
	      if(password<=99999)
	    	  password=999999-password;
	      return password;
    }

	public SendMailService getSendEmailService() {
		return sendEmailService;
	}

	public void setSendEmailService(SendMailService sendEmailService) {
		this.sendEmailService = sendEmailService;
	}

	@Override
	public void updateUser(UserTo user) {
		// TODO Auto-generated method stub
		userDao.saveOrUpdateUser(user);
	}

	@Override
	public String findPassword(String email) {
		// TODO Auto-generated method stub
		UserTo userTo=userDao.getUserByEmail(email);
		if(userTo!=null){
			String message="您的密码为："+userTo.getPassword();
			String title="小蚂蚁看板密码找回";
			sendEmailService.sender(email, message,title);
			return "SUCCESS";
		}
		else {
		    return "FAIL";
		}
		
	}

	@Override
	public List<UserTo> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}
    
    
}
