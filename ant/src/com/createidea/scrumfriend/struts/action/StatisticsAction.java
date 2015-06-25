package com.createidea.scrumfriend.struts.action;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.HashMap;


import com.createidea.scrumfriend.service.statistics.StatisticsService;

public class StatisticsAction extends BaseAction {
    
	//private JFreeChart chart;
	private StatisticsService statisticsService;
	private String projectId;
	private String sprintId;
    private float[] totalPointOfPoject;
    private float[] remainingPointOfProject;
    private String[] sprintsEndDate;
    private float[] completedPointOfSprint;
    private String[] sprintNames;
	 

	public String prepareProjectBurnDownChart(){
		 HashMap dateHashMap=statisticsService.createDataForProjectBurnDown(projectId);
		 totalPointOfPoject=(float[])dateHashMap.get("totalPointOfPoject");
		 remainingPointOfProject=(float[])dateHashMap.get("remainingPointOfProject");
		 sprintsEndDate=(String[])dateHashMap.get("sprintsEndDate");
		 return SUCCESS;
	}
	
	public String getDataForTeamVelocity(){
		HashMap dateHashMap=(HashMap)statisticsService.getDataForTeamVelocity(projectId);
		completedPointOfSprint=(float[])dateHashMap.get("completedPointOfSprints");
		sprintsEndDate=(String[])dateHashMap.get("sprints");
		return SUCCESS;
	}
	
	
	
	
	/*get set methods
	 * *
	 */

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSprintId() {
		return sprintId;
	}

	public void setSprintId(String sprintId) {
		this.sprintId = sprintId;
	}


	public float[] getTotalPointOfPoject() {
		return totalPointOfPoject;
	}


	public void setTotalPointOfPoject(float[] totalPointOfPoject) {
		this.totalPointOfPoject = totalPointOfPoject;
	}


	public float[] getRemainingPointOfProject() {
		return remainingPointOfProject;
	}


	public void setRemainingPointOfProject(float[] remainingPointOfProject) {
		this.remainingPointOfProject = remainingPointOfProject;
	}


	public String[] getSprintsEndDate() {
		return sprintsEndDate;
	}


	public void setSprintsEndDate(String[] sprintsEndDate) {
		this.sprintsEndDate = sprintsEndDate;
	}

	public float[] getCompletedPointOfSprint() {
		return completedPointOfSprint;
	}

	public void setCompletedPointOfSprint(float[] completedPointOfSprint) {
		this.completedPointOfSprint = completedPointOfSprint;
	}

	public String[] getSprintNames() {
		return sprintNames;
	}

	public void setSprintNames(String[] sprintNames) {
		this.sprintNames = sprintNames;
	}

	
	
}
