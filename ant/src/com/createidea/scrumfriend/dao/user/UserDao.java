package com.createidea.scrumfriend.dao.user;

import java.util.List;

import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.UserTo;

public interface UserDao {

	public UserTo getUserByName(String name);

	public UserTo createUser(String username, String password ,String email);

	public void removeUser(UserTo userTo);

	public UserTo getRandomUser();

	public void saveOrUpdateUser(UserTo user);

	public UserTo getUserById(String userId);

	public void setDefaultProject(String userId, String projectId);

	public UserTo getUserByEmail(String userEmail);
	
	public List<UserTo> getAllUsers();

}
