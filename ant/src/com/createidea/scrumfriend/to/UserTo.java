package com.createidea.scrumfriend.to;

import java.util.HashSet;
import java.util.Set;

public class UserTo {
	private String id;
	private String name;
	private String password;
	private ProjectTo defaultProject;
	private Set projects=new HashSet();
	private String email;
	private AttachmentTo photo;
	
	public UserTo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserTo(String userId) {
		// TODO Auto-generated constructor stub
		this.id=userId;
	}
	
	public UserTo(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTo other = (UserTo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public ProjectTo getDefaultProject() {
		return defaultProject;
	}
	public void setDefaultProject(ProjectTo defaultProject) {
		this.defaultProject = defaultProject;
	}
	public Set getProjects() {
		return projects;
	}
	public void setProjects(Set projects) {
		this.projects = projects;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public AttachmentTo getPhoto() {
		return photo;
	}
	
	public void setPhoto(AttachmentTo photo) {
		this.photo = photo;
	}
	
	
}
