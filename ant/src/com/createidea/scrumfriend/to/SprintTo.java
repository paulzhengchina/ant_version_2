package com.createidea.scrumfriend.to;

import java.util.Date;
import java.util.Set;

public class SprintTo {
	private String id;
	private String name;
	private Date startTime;
	private Date endTime;
	private String status;
	private ProjectTo project;
	private StatisticsSprintTo statistics;
	private SprintTo parentSprint;
	private Set<SprintTo> subSprints;
	
	public SprintTo(String id) {
		this.id = id;
	}
	
	
	public SprintTo() {
		super();
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ProjectTo getProject() {
		return project;
	}
	public void setProject(ProjectTo project) {
		this.project = project;
	}
	public StatisticsSprintTo getStatistics() {
		return statistics;
	}
	public void setStatistics(StatisticsSprintTo statistics) {
		this.statistics = statistics;
	}
	public SprintTo getParentSprint() {
		return parentSprint;
	}
	public void setParentSprint(SprintTo parentSprint) {
		this.parentSprint = parentSprint;
	}


	public Set<SprintTo> getSubSprints() {
		return subSprints;
	}


	public void setSubSprints(Set<SprintTo> subSprints) {
		this.subSprints = subSprints;
	}


}
