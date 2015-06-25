package com.createidea.scrumfriend.to;

public class StatisticsSprintTo {
	private String id;
    private float committedStoryPoint;
    private float completedStoryPoint;
    private float remainingStoryPoint;
    private float totalEffort;
    private float remainingEffort;
    private int issuesNumber;
    private SprintTo sprint;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getCommittedStoryPoint() {
		return committedStoryPoint;
	}
	public void setCommittedStoryPoint(float committedStoryPoint) {
		this.committedStoryPoint = committedStoryPoint;
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
	public float getTotalEffort() {
		return totalEffort;
	}
	public void setTotalEffort(float totalEffort) {
		this.totalEffort = totalEffort;
	}
	public float getRemainingEffort() {
		return remainingEffort;
	}
	public void setRemainingEffort(float remainingEffort) {
		this.remainingEffort = remainingEffort;
	}
	public int getIssuesNumber() {
		return issuesNumber;
	}
	public void setIssuesNumber(int issuesNumber) {
		this.issuesNumber = issuesNumber;
	}
	public SprintTo getSprint() {
		return sprint;
	}
	public void setSprint(SprintTo sprint) {
		this.sprint = sprint;
	}
    
    
}
