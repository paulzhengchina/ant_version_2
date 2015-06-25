package com.createidea.scrumfriend.service.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.createidea.scrumfriend.dao.project.ProjectDao;
import com.createidea.scrumfriend.dao.user.UserDao;
import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.UserTo;

public class ProjectServiceImpl implements ProjectService {

	private ProjectDao projectDao;
	private UserDao userDao;
	private UserService userService;

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public void createProject(ProjectTo project) {
		// TODO Auto-generated method stub
		Set users=project.getUsers();
		users.add(project.getUser());
		project.setUsers(users);
		projectDao.saveProject(project);
	}

	@Override
	public List<ProjectTo> getActiveProjectsForUser(String userId) {
		// TODO Auto-generated method stub
		List<ProjectTo> projectList = new ArrayList<ProjectTo>();
		UserTo user = userDao.getUserById(userId);
		if (user != null) {
			Set<ProjectTo> projects = userDao.getUserById(userId).getProjects();
			for(ProjectTo projectTo : projects){
				if(projectTo.getStatus()==ProjectTo.NORMAL_STATUS)
					projectList.add(projectTo);
			}
		}
		return projectList;
	}

	@Override
	public void deleteProject(String userId,String projectId) {
		ProjectTo project=getProjectById(projectId);
		project.setStatus(1);
		projectDao.updateProject(project);
		
	}

	@Override
	public ProjectTo getProjectById(String id) {
		
		return projectDao.getProjectById(id);
	}

	@Override
	public void updateProject(ProjectTo projectTo) {
		ProjectTo oldProject=projectDao.getProjectById(projectTo.getId());
		Set users=oldProject.getUsers();
		projectTo.setUsers(users);
		if("".equals(projectTo.getLogo().getId()))
			projectTo.setLogo(null);
		projectDao.updateProject(projectTo);
		
	}
	
	@Override
	public void createANewMemeberForProject(String userEmail, String projectId) {
		// TODO Auto-generated method stub
		UserTo newMember=userDao.getUserByEmail(userEmail);
		if(newMember==null)
		{
			userService.registerUser(userEmail);
			newMember=userDao.getUserByEmail(userEmail);
		}
		ProjectTo projectTo=projectDao.getProjectById(projectId);
		if(projectTo!=null)
		projectTo.getUsers().add(newMember);
		projectDao.updateProject(projectTo);		
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void removeAMemberFromProject(String userId, String projectId) {
		// TODO Auto-generated method stub
		ProjectTo project=projectDao.getProjectById(projectId);
		UserTo user=userDao.getUserById(userId);
		if(project.getUser().getId().equals(userId))
		{
			projectDao.deleteProject(project);
			return;
		}
		Set users=project.getUsers();
		users.remove(user);
		project.setUsers(users);
		if(user.getDefaultProject()!=null && user.getDefaultProject().getId().equals(project.getId()))
		{
			user.setDefaultProject(null);
		    userDao.saveOrUpdateUser(user);
		}
		projectDao.updateProject(project);
		
	}

	

	

	
	  
	  
}
