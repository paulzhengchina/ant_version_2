package com.createidea.scrumfriend.struts.action;

import com.createidea.scrumfriend.service.story.StoryService;
import com.createidea.scrumfriend.service.task.TaskService;
import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.TaskTo;
import com.createidea.scrumfriend.to.UserTo;

public class TaskAction extends BaseAction {
	
	private StoryService storyService;
	private UserService userService;
	private TaskService taskService;
	private TaskTo task;
	private String storyId;
	private String taskId;
	private String taskStatus;
	private String effort;
	private String userId;
	private String title;
	
	public String addTask() {
		return SUCCESS;
	}
	
	public String saveTask(){
		task = taskService.saveTask(task);
		return SUCCESS;
	}
	
	public String updateTaskStatus(){
	    taskService.updateTaskStatus(taskId,taskStatus);
	    return SUCCESS;
	   
	}
	
	public String updateEffort(){
		taskService.updateEffort(taskId,effort);
		return SUCCESS;
	}
	
	public String updatePerformer(){
		taskService.updatePerformer(taskId,userId);
		return SUCCESS;
	}
	
	public String updateTitle(){
		taskService.updateTitle(taskId,title);
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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	
}
