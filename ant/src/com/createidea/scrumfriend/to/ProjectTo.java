package com.createidea.scrumfriend.to;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class ProjectTo {
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     private String id;
     private String name;
     private Date start_time;
     private Date end_time;
     private UserTo user;
     private String description;
     private int status;// 1:normal; 0:deleted
     private Set users= new HashSet();
     private StatisticsProjectTo statistics;
     private AttachmentTo logo;
     public static final int NORMAL_STATUS=0;
     public static final int DELETED_STATUS=1;
     
	
	public ProjectTo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectTo(String projectId) {
		this.id=projectId;
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
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public UserTo getUser() {
		return user;
	}
	public void setUser(UserTo user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Set getUsers() {
		return users;
	}
	public void setUsers(Set users) {
		this.users = users;
	}
	public StatisticsProjectTo getStatistics() {
		return statistics;
	}
	public void setStatistics(StatisticsProjectTo statistics) {
		this.statistics = statistics;
	}
	public AttachmentTo getLogo() {
		return logo;
	}
	public void setLogo(AttachmentTo logo) {
		this.logo = logo;
	}

     
}
