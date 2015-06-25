package com.createidea.scrumfriend.to;

import java.util.Date;

public class StatisticsProjectTo { 
	private String id;
	private float totalStoryPoint;
	private float completedStoryPoint;
	private float remainingStoryPoint;
	private ProjectTo project;
	private int sprintSerial;
	private Date date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getTotalStoryPoint() {
		return totalStoryPoint;
	}
	public void setTotalStoryPoint(float totalStoryPoint) {
		this.totalStoryPoint = totalStoryPoint;
	}
	public float getCompletedStoryPoint() {
		return completedStoryPoint;
	}
	public void setCompletedStoryPoint(float completedStoryPoint) {
		this.completedStoryPoint = completedStoryPoint;
	}
	public float getRemainingStoryPoint() {
		return remainingStoryPoint;
	}
	public void setRemainingStoryPoint(float remainingStoryPoint) {
		this.remainingStoryPoint = remainingStoryPoint;
	}
	public ProjectTo getProject() {
		return project;
	}
	public void setProject(ProjectTo project) {
		this.project = project;
	}
	public int getSprintSerial() {
		return sprintSerial;
	}
	public void setSprintSerial(int sprintSerial) {
		this.sprintSerial = sprintSerial;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
