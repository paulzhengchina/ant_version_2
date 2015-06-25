package com.createidea.scrumfriend.dao.story;

import java.util.Date;
import java.util.List;

import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;


public interface StoryDao {

	public List<StoryTo> storysByStatus(int status);
	
	public void updateStory(StoryTo story);

	public StoryTo getStoryById(String card_id);

	public List<StoryTo> getActiveStoriesForProject(String projectId);

	public String createStory(StoryTo story);

	public List<StoryTo> getStoriesForProjectByStatus(String projectId,int status);

	public float calculateTotalPointsForProject(ProjectTo project);

	public float calculateCompletedPointForProject(ProjectTo project);

	public float calculateRemainingPointForProject(ProjectTo project);

	public float calculateCommittedStoryPoint(String sprintId);

	public float calculateCompletedStoryPoint(String sprintId);

	public List<StoryTo> getStoriesForSprintByStatus(String sprintId, int status);

	public List getStoriesForProjectBySearchWithName(String projectId,
			String query);

	public List<StoryTo> getStoriesForKanban(String sprintId);
	
	public float calculateStoriesTotalPointByPriority(int priority,String projectId);
	
	public float calculateStoryPoints(String projectId, int status,int priority);


}
