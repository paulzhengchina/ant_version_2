package com.createidea.scrumfriend.dao.project;

import java.util.List;

import com.createidea.scrumfriend.to.ProjectTo;

public interface ProjectDao {

	public void saveProject(ProjectTo project);
	public List<ProjectTo> getProject(String userId);
	public void deleteProject(ProjectTo project);
	public ProjectTo getProjectById(String id);
	public void updateProject(ProjectTo projectTo);
}
