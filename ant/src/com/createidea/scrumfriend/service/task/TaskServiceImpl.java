package com.createidea.scrumfriend.service.task;

import java.util.List;

import com.createidea.scrumfriend.dao.task.TaskDao;
import com.createidea.scrumfriend.to.TaskTo;
import com.createidea.scrumfriend.to.UserTo;

public class TaskServiceImpl implements TaskService {

	private TaskDao taskDao;

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public TaskTo saveTask(TaskTo task) {
		// TODO Auto-generated method stub
		TaskTo taskTo=null;;
		task.setStatus(0);
		String taskId=taskDao.saveTask(task);
		if(taskId!=null)
		   taskTo=taskDao.getTaskById(taskId);
		return taskTo;
	}
	
	public List<TaskTo> getTasksOfStory(String storyId) {
		return taskDao.getTasksOfStory(storyId);
	}

	@Override
	public void updateTaskStatus(String taskId, String taskStatus) {
		// TODO Auto-generated method stub
		TaskTo taskTo=taskDao.getTaskById(taskId);
		if(taskTo!=null&&taskStatus!=null)
		{
			taskTo.setStatus(Integer.parseInt(taskStatus));
			if(Integer.parseInt(taskStatus)==2)
				taskTo.setLeftEffort(0);
			taskDao.updateTask(taskTo);
		}
	}

	@Override
	public void updateEffort(String taskId, String effort) {
		// TODO Auto-generated method stub
		TaskTo taskTo=taskDao.getTaskById(taskId);
		if(taskTo!=null&&effort!=null)
		{
			if("".equals(effort))
				taskTo.setLeftEffort(0);
			else
			    taskTo.setLeftEffort(Float.parseFloat(effort));
			taskDao.updateTask(taskTo);
		}
	}

	@Override
	public void updatePerformer(String taskId, String userId) {
		// TODO Auto-generated method stub
		TaskTo taskTo=taskDao.getTaskById(taskId);
		UserTo performer=new UserTo();
		if(taskTo!=null&&userId!=null)
		{
			performer.setId(userId);
			taskTo.setPerformer(performer);
			taskDao.updateTask(taskTo);
		}
	}

	@Override
	public void updateTitle(String taskId, String title) {
		// TODO Auto-generated method stub
		TaskTo taskTo=taskDao.getTaskById(taskId);
		if(taskTo!=null&&title!=null)
		{
			if(!"".equals(title))
			{
			   taskTo.setTitle(title);
			   taskDao.updateTask(taskTo);
			}
		}
	}

}
