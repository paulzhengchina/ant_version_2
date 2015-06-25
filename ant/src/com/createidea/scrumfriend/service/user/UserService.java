package com.createidea.scrumfriend.service.user;

import java.util.List;

import com.createidea.scrumfriend.to.UserTo;

public interface UserService {

	public UserTo getUserByName(String name);

	public String registerUser(String email);

	public boolean hasDefaultProject(UserTo user);

	public UserTo getUserById(String userId);

	public void setDefaultProject(String userId, String projectId);

	public UserTo getUserByEmail(String email);

	public void updateUser(UserTo user);

	public String findPassword(String email);
	
	public List<UserTo> getAllUsers();

}
