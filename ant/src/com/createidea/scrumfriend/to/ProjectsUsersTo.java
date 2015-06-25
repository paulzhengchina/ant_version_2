package com.createidea.scrumfriend.to;

public class ProjectsUsersTo {
	private String id;
	private ProjectTo project;
	private UserTo user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjectTo getProject() {
		return project;
	}

	public void setProject(ProjectTo project) {
		this.project = project;
	}

	public UserTo getUser() {
		return user;
	}

	public void setUser(UserTo user) {
		this.user = user;
	}

}
