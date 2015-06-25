package com.createidea.scrumfriend.service.statistics;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StatisticsProjectTo;

public interface StatisticsService {
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
	
	public void createStatisticsForProject(SprintTo sprint);

	public List<StatisticsProjectTo> getProjectStatistics(String projectId);
	
	public  HashMap createDataForProjectBurnDown(String projectId);

	public float calculateCommittedStoryPoint(SprintTo sprint);

	public float calculateCompletedStoryPoint(SprintTo sprint);

	public float calculateTotalEffortForSprint(SprintTo sprint);

	public float calculateRemainingEffortForSprint(SprintTo sprint);

	public void updateStatisticsForSprint(SprintTo sprint);
	
	public void updateStatisticsForAllProjects();

	public Map getDataForTeamVelocity(String projectId);
	
	public Map<String,Integer> getStatisticsForStoryPriorities(String projectId);
	
	public Map<String,Integer> getStatisticsForStoryStatuses(String projectId);

}
