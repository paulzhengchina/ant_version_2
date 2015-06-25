package com.createidea.scrumfriend.struts.action;

import java.util.List;

import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.UserTo;
import com.createidea.scrumfriend.utils.email.SendMailService;

public class AdminAction extends BaseAction {
    private UserService userService;
    private SendMailService sendEmailService = new SendMailService(); 
    
	public String loadConsole()
	{
		return SUCCESS;
	}
	
	public String sendUpdateInfo()
	{
	//	List<UserTo> allRegistedUsers= userService.getAllUsers();
		sendEmailService.sendWithHtmlContent("zheng.renhua@163.com", getApplicationRootPath());

		
		//for(UserTo user : allRegistedUsers)
		//{
		//	sendEmailService.sender(user.getEmail(), "test", "Test");
		//}
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SendMailService getSendEmailService() {
		return sendEmailService;
	}

	public void setSendEmailService(SendMailService sendEmailService) {
		this.sendEmailService = sendEmailService;
	}
	
	
}
