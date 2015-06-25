package com.createidea.scrumfriend.struts.action;

import java.util.ArrayList;
import java.util.List;

import com.createidea.scrumfriend.service.project.ProjectService;
import com.createidea.scrumfriend.service.sprint.SprintService;
import com.createidea.scrumfriend.service.statistics.StatisticsService;
import com.createidea.scrumfriend.service.story.StoryService;
import com.createidea.scrumfriend.service.task.TaskService;
import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;
import com.createidea.scrumfriend.to.TaskTo;
import com.createidea.scrumfriend.to.TreeNodeTo;
import com.createidea.scrumfriend.to.UserTo;

public class SprintAction extends BaseAction {
	private SprintService sprintService;
	private String projectId;
	private SprintTo sprint;
	private List<SprintTo> sprints;
	private String sprintId;
	private StoryService storyService;
	private UserService userService;
	private List<StoryTo> storysByStatus0;
	private List<StoryTo> storysByStatus1;
	private String box_id;
	private String card_id;
	private List<StoryTo> stories;
	private static final String REDIRECT_TO_LoadKanban="redirect_to_load_kanban";
	private StatisticsService statisticsService;
	private List<TaskTo> tasksOfStory;
	private String storyId;
	private TaskService taskService;
	private TaskTo task;
	private ProjectTo project;
	private ProjectService projectService;
	
	public String updateSprint(){
		sprint=sprintService.updateSprint(sprint);
		projectId=sprint.getProject().getId();
		return SUCCESS;
	}
	public String modifySprint(){
		sprint = sprintService.getSprintById(sprintId);
		return SUCCESS;
	}
	public String deleteSprint(){
		sprintService.deleteSprint(sprintId);
		return SUCCESS;
	}

	public String createSprint(){
		return SUCCESS;
	}
	

	public String saveSprint(){
		sprintService.createSprint(sprint, projectId, sprintId);
		return SUCCESS;
	}
	
	public String loadKanban() {
		if(sprintId==null||"".equals(sprintId)){
			if(projectId==null||"".equals(projectId))
				projectId=userService.getUserById((String)this.getSession().getAttribute(this.USER)).getDefaultProject().getId();
			sprint=sprintService.getCurrentSprint(projectId);
			if(sprint!=null){
				sprintId=sprint.getId();
				project=sprint.getProject();
				storysByStatus1= storyService.getStoriesForKanban(sprintId);
			}
			else{
				project=projectService.getProjectById(projectId);
			}
			
		}
		else {
			sprint=sprintService.getSprintById(sprintId);
			storysByStatus1= storyService.getStoriesForKanban(sprintId);
			project=sprint.getProject();		
		}
		projectId=project.getId();
		return SUCCESS;
	}
	
	public String ajaxStoryUpdate() {
		if(projectId==null)
			projectId=userService.getUserById((String)this.getSession().getAttribute(this.USER)).getDefaultProject().getId();		
		storyService.updateStoryStatus(card_id,box_id,sprintId,this.USER);
		sprint=sprintService.getSprintById(sprintId);
	//	statisticsService.updateStatisticsForSprint(sprint);
		stories=storyService.getStoriesForProjectByStatus(projectId, Integer.parseInt(box_id.substring(3)));
		return SUCCESS;
	}
	
	public String saveTask() {
		task.setPerformer(new UserTo((String)this.getSession().getAttribute(this.USER)));
		taskService.saveTask(task);
		tasksOfStory = taskService.getTasksOfStory(storyId);
		return SUCCESS;
	}
	
	public String showAllSprintsForProject(){
		sprints=sprintService.getParentSprints(projectId);
		project=projectService.getProjectById(projectId);
		return SUCCESS;
	}
	/*
	 * get set method
	 */
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public SprintTo getSprint() {
		return sprint;
	}
	public void setSprint(SprintTo sprint) {
		this.sprint = sprint;
	}
	public String getSprintId() {
		return sprintId;
	}

	public void setSprintId(String sprintId) {
		this.sprintId = sprintId;
	}
	public StoryService getStoryService() {
		return storyService;
	}
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public List<StoryTo> getStorysByStatus0() {
		return storysByStatus0;
	}
	public void setStorysByStatus0(List<StoryTo> storysByStatus0) {
		this.storysByStatus0 = storysByStatus0;
	}
	public List<StoryTo> getStorysByStatus1() {
		return storysByStatus1;
	}
	public void setStorysByStatus1(List<StoryTo> storysByStatus1) {
		this.storysByStatus1 = storysByStatus1;
	}
	public String getBox_id() {
		return box_id;
	}
	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public List<StoryTo> getStories() {
		return stories;
	}
	public void setStories(List<StoryTo> stories) {
		this.stories = stories;
	}
	public StatisticsService getStatisticsService() {
		return statisticsService;
	}
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	public List<TaskTo> getTasksOfStory() {
		return tasksOfStory;
	}
	public void setTasksOfStory(List<TaskTo> tasksOfStory) {
		this.tasksOfStory = tasksOfStory;
	}
	public String getStoryId() {
		return storyId;
	}
	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public TaskTo getTask() {
		return task;
	}
	public void setTask(TaskTo task) {
		this.task = task;
	}
	public ProjectTo getProject() {
		return project;
	}
	public void setCurrentProject(ProjectTo project) {
		this.project = project;
	}
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
}
