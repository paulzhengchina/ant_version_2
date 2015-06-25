package com.createidea.scrumfriend.dao.task;

import java.util.List;

import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.TaskTo;


public interface TaskDao {
	
	public String saveTask(TaskTo task);

	public float calculateRemainingEffortForSprint(SprintTo sprint);
	
	public List<TaskTo> getTasksOfStory(String storyId);

	public TaskTo getTaskById(String taskId);
	
	public void updateTask(TaskTo task);

}
