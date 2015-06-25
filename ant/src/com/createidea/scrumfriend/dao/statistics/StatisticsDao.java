package com.createidea.scrumfriend.dao.statistics;

import java.util.Date;
import java.util.List;

import com.createidea.scrumfriend.to.StatisticsDateTo;
import com.createidea.scrumfriend.to.StatisticsProjectTo;
import com.createidea.scrumfriend.to.StatisticsSprintTo;

public interface StatisticsDao {

	void saveStatistics(StatisticsProjectTo statistics);

	List<StatisticsProjectTo> getProjectStatistics(String projectId);

	void updateSprintStatistics(StatisticsSprintTo statistics);

	List<StatisticsSprintTo> getSprintsStatisticsForProject(String projectId);

	StatisticsDateTo getStatisticsDateByDate(Date date);

	void saveOrUpdateStatisticsDate(StatisticsDateTo statisticsDate);

	List<StatisticsDateTo> getStatisticsDateForSprint(String sprintId);
}
