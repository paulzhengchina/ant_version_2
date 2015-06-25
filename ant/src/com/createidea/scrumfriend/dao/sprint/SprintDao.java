package com.createidea.scrumfriend.dao.sprint;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;


public interface SprintDao {
	
	public void updateSprint(SprintTo sprint);

	public SprintTo getSprintById(String sprintId);

	public List<SprintTo> getSprintForProject(String projectId);

	public SprintTo createSprint(SprintTo sprint,String projectId);

	public List<SprintTo> getSprintsForProjectByStatus(String projectId,int status);

	public void deleteSprint(String sprintId);

	public List<SprintTo> getSprintsFinishedYesterday(Date today,Date twoDaysAgo);

	public List<SprintTo> getParentSprints(String projectId);
	
	public List<SprintTo> getCurrentSprints(String projectId);

}
