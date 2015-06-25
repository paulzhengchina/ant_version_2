package com.createidea.scrumfriend.to;

import java.sql.Date;

public class StatisticsDateTo {
    public String id;
    public Date date;
    public float remainingEffort;
    public float remainingPoints;
    public SprintTo sprint;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getRemainingEffort() {
		return remainingEffort;
	}
	public void setRemainingEffort(float remainingEffort) {
		this.remainingEffort = remainingEffort;
	}
	public float getRemainingPoints() {
		return remainingPoints;
	}
	public void setRemainingPoints(float remainingPoints) {
		this.remainingPoints = remainingPoints;
	}
	public SprintTo getSprint() {
		return sprint;
	}
	public void setSprint(SprintTo sprint) {
		this.sprint = sprint;
	}
    
    
    
    
}
