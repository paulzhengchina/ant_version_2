package com.createidea.scrumfriend.struts.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.createidea.scrumfriend.service.attachment.AttachmentService;
import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.AttachmentTo;
import com.createidea.scrumfriend.to.UserTo;

public class UserAction extends BaseAction {
	
	private UserTo user;
	private UserService userService;
	private String username;
	private String password;
	private String projectId;
	private String email;
	private boolean ok;
	private static final String REDIRECT_DEFAULT_PROJECT="redirect_default_project";
	private static final String REDIRECT_PROJECT_PAGE="redirect_project_page";
	private String result;
	private File photo;
	private String photoFileName;
	private AttachmentService attachmentService;
	private String defaulProjectId;
	
	public String verifyUser(){
		UserTo proposedUser=userService.getUserByEmail(user.getEmail());
		if(proposedUser!=null && proposedUser.getPassword().equals(user.getPassword())){
		   this.getSession().setAttribute(this.USER, proposedUser.getId());
		  
		   if(userService.hasDefaultProject(proposedUser))
		   {
			   defaulProjectId=proposedUser.getDefaultProject().getId();
			   return REDIRECT_DEFAULT_PROJECT;
		   }
		   else
		       return REDIRECT_PROJECT_PAGE;
		 
		}
		else
		{
			this.addActionMessage("用户名密码错误!");
			return INPUT;
		}
	}

	public String setDefaultProject(){
		userService.setDefaultProject((String)this.getSession().getAttribute(this.USER),projectId);
		ok = true;
		return SUCCESS;
	}
	public String loadRegister(){
		return SUCCESS;
	}
	
	public String registerUser(){
		result=userService.registerUser(email);
		return SUCCESS;
		
	}
	
	public String findPassword(){
		result= userService.findPassword(email);
		return SUCCESS;
	}
	public String getDefaultProject(){
		
		user=userService.getUserById((String)this.getSession().getAttribute(this.USER));
		if(user!=null)
			projectId=user.getDefaultProject().getId();
		return SUCCESS;
	}
	
	public String logout(){
		this.getSession().removeAttribute(this.USER);
		return SUCCESS;
	}
	
	public String setting(){
		user=userService.getUserById((String)this.getSession().getAttribute(this.USER));
		return SUCCESS;
	}
	
	public String updateUserName(){
		user=userService.getUserById((String)this.getSession().getAttribute(this.USER));
		 try {
			 username=URLDecoder.decode(username,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setName(username);
		userService.updateUser(user);
		return SUCCESS;
	}
	
	public String updateUserPassword(){
		user=userService.getUserById((String)this.getSession().getAttribute(this.USER));
		user.setPassword(password);
		userService.updateUser(user);
		return SUCCESS;
	}
	
	public String updatePhoto(){
		AttachmentTo photoAttachment=attachmentService.saveAttachment(photo, null, photoFileName, this.getRequest().getRealPath("/"));
		user=userService.getUserById((String)this.getSession().getAttribute(this.USER));
		user.setPhoto(photoAttachment);
		userService.updateUser(user);
		photoFileName=photoAttachment.getPath();
		return SUCCESS;
	}
	/*
	 * get and set methods 
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public boolean isOk() {
		return ok;
	}
	public UserTo getUser() {
		return user;
	}

	public void setUser(UserTo user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public String getDefaulProjectId() {
		return defaulProjectId;
	}

	public void setDefaulProjectId(String defaulProjectId) {
		this.defaulProjectId = defaulProjectId;
	}
	
	
}
