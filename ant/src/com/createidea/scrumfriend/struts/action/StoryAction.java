package com.createidea.scrumfriend.struts.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.createidea.scrumfriend.service.project.ProjectService;
import com.createidea.scrumfriend.service.sprint.SprintService;
import com.createidea.scrumfriend.service.story.StoryService;
import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;

public class StoryAction extends BaseAction {
	
	private StoryService storyService;	
	private StoryTo story;
	private String projectId;
	private List<StoryTo> stories;
	private String storyId;
	private String storyStatus;
	private SprintService sprintService;
	private List<SprintTo> sprints;
	private SprintTo currentSprint;
	private ProjectTo project;
	private ProjectService projectService;
	
	public String loadCreateStory(){
		return SUCCESS;
	}
	
	public String createStory(){
		story.setStatus(0);
		story=storyService.createStory(story,projectId);
		return SUCCESS;
	}
	
	public String viewStoriesOfProject(){
		stories=storyService.getActiveStoriesForProject(projectId);
		if(stories!=null&&stories.size()>0)
			project=stories.get(0).getProject();
		else {
			project=projectService.getProjectById(projectId);
		}
		return SUCCESS;
	}
	
	public String loadEditStory(){
		story=storyService.getStoryById(storyId);
		sprints=sprintService.getParentSprints(story.getProject().getId());
		currentSprint=sprintService.getCurrentSprint(story.getProject().getId());
		return SUCCESS;
	}
	
	public String updateStory(){
		storyService.updateStory(story);
		projectId=story.getProject().getId();
		return SUCCESS;
	}
	
	public String deleteStory(){
		story=storyService.getStoryById(storyId);
		story.setStatus(2);
		storyService.updateStory(story);
		return SUCCESS;
	}
	
	public String showStory(){
		story=storyService.getStoryById(storyId);
		return SUCCESS;
	}
	
	public String updateStoryStatus(){
		storyService.updateStoryStatus(storyId,Integer.parseInt(storyStatus));
		return SUCCESS;
	}
	/*
	 * set & get methods
	 */
	
	
	public StoryService getStoryService() {
		return storyService;
	}
	
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}

	
	
	public StoryTo getStory() {
		return story;
	}
	
	public void setStory(StoryTo story) {
		this.story = story;
	}
	
	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public List<StoryTo> getStories() {
		return stories;
	}
	
	public void setStories(List<StoryTo> stories) {
		this.stories = stories;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getStoryStatus() {
		return storyStatus;
	}

	public void setStoryStatus(String storyStatus) {
		this.storyStatus = storyStatus;
	}

	public SprintService getSprintService() {
		return sprintService;
	}

	public void setSprintService(SprintService sprintService) {
		this.sprintService = sprintService;
	}

	public List<SprintTo> getSprints() {
		return sprints;
	}

	public void setSprints(List<SprintTo> sprints) {
		this.sprints = sprints;
	}

	public SprintTo getCurrentSprint() {
		return currentSprint;
	}

	public void setCurrentSprint(SprintTo currentSprint) {
		this.currentSprint = currentSprint;
	}

	public ProjectTo getProject() {
		return project;
	}

	public void setProject(ProjectTo project) {
		this.project = project;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	
	
	
}
