package com.createidea.scrumfriend.to;

import java.util.HashSet;
import java.util.Set;

public class StoryTo {
	private String id;
	private String name;
	private int priority;//0:Must Have,1:Should Have,2:Cound Hava,3 Won't Have(But Want)
	private int point;
	private int status; //0:new,1:done,2:removed
	private int businessValue;
	private String dod;
	private SprintTo sprint;
	private ProjectTo project;
	private Set<TaskTo> tasks = new HashSet<TaskTo>();
	private int priorityNum =0;//the relative number of priority,and the stories will be ordered by it
	
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDod() {
		return dod;
	}
	public void setDod(String dod) {
		this.dod = dod;
	}
	public ProjectTo getProject() {
		return project;
	}
	public void setProject(ProjectTo project) {
		this.project = project;
	}
	public Set<TaskTo> getTasks() {
		return tasks;
	}
	public void setTasks(Set<TaskTo> tasks) {
		this.tasks = tasks;
	}
	public SprintTo getSprint() {
		return sprint;
	}
	public void setSprint(SprintTo sprint) {
		this.sprint = sprint;
	}
	public int getBusinessValue() {
		return businessValue;
	}
	public void setBusinessValue(int businessValue) {
		this.businessValue = businessValue;
	}
	public int getPriorityNum() {
		return priorityNum;
	}
	public void setPriorityNum(int priorityNum) {
		this.priorityNum = priorityNum;
	}
	
	
}
