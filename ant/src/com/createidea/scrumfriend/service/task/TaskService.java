package com.createidea.scrumfriend.service.task;

import java.util.List;

import com.createidea.scrumfriend.to.StoryTo;
import com.createidea.scrumfriend.to.TaskTo;

public interface TaskService {
	
	public TaskTo saveTask(TaskTo task);
	
	public List<TaskTo> getTasksOfStory(String storyId);

	public void updateTaskStatus(String taskId, String taskStatus);

	public void updateEffort(String taskId, String effort);

	public void updatePerformer(String taskId, String userId);

	public void updateTitle(String taskId, String title);
}
