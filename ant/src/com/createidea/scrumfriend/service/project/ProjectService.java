package com.createidea.scrumfriend.service.project;

import java.util.List;

import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.UserTo;

public interface ProjectService {
	public void createProject(ProjectTo project);
	public List<ProjectTo> getActiveProjectsForUser(String  userId);
	public void deleteProject(String userId,String projectId);
	public ProjectTo getProjectById(String id);
	public void updateProject(ProjectTo projectTo);
	public void createANewMemeberForProject(String userEmail, String projectId);
	public void removeAMemberFromProject(String userId, String projectId);
}
