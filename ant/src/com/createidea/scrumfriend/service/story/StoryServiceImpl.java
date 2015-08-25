package com.createidea.scrumfriend.service.story;

import java.util.ArrayList;
import java.util.List;

import com.createidea.scrumfriend.dao.story.StoryDao;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;

public class StoryServiceImpl implements StoryService {

	private StoryDao storyDao;

	public StoryDao getStoryDao() {
		return storyDao;
	}

	public void setStoryDao(StoryDao storyDao) {
		this.storyDao = storyDao;
	}

	
	@Override
	public void updateStory(StoryTo story) {
		// TODO Auto-generated method stub
	//	story.setStatus(3);
		if(story.getSprint()!=null&&"".equals(story.getSprint().getId()))
		story.setSprint(null);
		StoryTo originalStory=storyDao.getStoryById(story.getId());
		story.setTasks(originalStory.getTasks());
		storyDao.updateStory(story);
	}

	@Override
	public void updateStoryStatus(String card_id, String box_id,String sprintId, String user) {
		// TODO Auto-generated method stub
		StoryTo story=storyDao.getStoryById(card_id);
	
		int status=Integer.parseInt(box_id.substring(3));
		if(status==0)
			story.setSprint(null);
		else
			story.setSprint(new SprintTo(sprintId));
		story.setStatus(status);		
		storyDao.updateStory(story);
	}

	@Override
	public StoryTo createStory(StoryTo story,String projectId) {
		story.setStatus(0);
		story.setPriorityNum(storyDao.getMaxPriorityNum(projectId)+1);
		if(projectId!=null)
			story.setProject(new ProjectTo(projectId));
		String storyId =storyDao.createStory(story);
		story=storyDao.getStoryById(storyId);
		return story;
	}

	@Override
	public List<StoryTo> getActiveStoriesForProject(String projectId) {
		
		return storyDao.getActiveStoriesForProject(projectId);
	}

	@Override
	public List<StoryTo> getStoriesForProjectByStatus(String projectId,int status) {
		
		return storyDao.getStoriesForProjectByStatus(projectId,status);
		 
	}

	
	@Override
	public List<StoryTo> getStoriesForSprintByStatus(String sprintId, int status) {
		// TODO Auto-generated method stub
		return storyDao.getStoriesForSprintByStatus(sprintId,status);
	}

	

	@Override
	public StoryTo getStoryById(String storyId) {
		// TODO Auto-generated method stub
		return storyDao.getStoryById(storyId);
	}

	@Override
	public void updateStoryStatus(String storyId, int storyStatus) {
		// TODO Auto-generated method stub
		StoryTo story=storyDao.getStoryById(storyId);
		if(story!=null){
			story.setStatus(storyStatus);
			storyDao.updateStory(story);
		}
	}

	@Override
	public List<StoryTo> getStoriesForKanban(String sprintId) {
		// TODO Auto-generated method stub
		return storyDao.getStoriesForKanban(sprintId);
	}

	@Override
	public float calculateStoryPoints(ProjectTo project, int status,int priority) {
		// TODO Auto-generated method stub
		return storyDao.calculateStoryPoints(project.getId(), status, priority);
	}

	@Override
	public void updatePriority(String projectId, String draggingStoryId,String beforeGraggingStoryId, String afterGraggingStoryId) {
		// TODO Auto-generated method stub
		StoryTo draggingStoryTo=storyDao.getStoryById(draggingStoryId);
		StoryTo afterGraggingStory=storyDao.getStoryById(afterGraggingStoryId);
		
		if(beforeGraggingStoryId==null)
		{
			draggingStoryTo.setPriorityNum(afterGraggingStory.getPriorityNum()+1);
			storyDao.updateStory(draggingStoryTo);
			return;
		}
		
		StoryTo beforeGraggingStory=storyDao.getStoryById(beforeGraggingStoryId);		
		String updateOtherStoryPrioritySql="update story set priority_num=(priority_num+1) where project_id='"+projectId+"' and priority_num>='"+beforeGraggingStory.getPriorityNum()+"'";
		storyDao.updatePriority(updateOtherStoryPrioritySql);
		
		draggingStoryTo.setPriorityNum(beforeGraggingStory.getPriorityNum());
		storyDao.updateStory(draggingStoryTo);
		
	}

	@Override
	public List<StoryTo> getFilteredStories(String projectId, String filterItems) {
		// TODO Auto-generated method stub
		ArrayList<Integer> priorities=new ArrayList<Integer>();
		ArrayList<Integer> statuses=new ArrayList<Integer>();
		if(filterItems.length()>2){
			String[] filterItem=filterItems.split(",");
			for(String item : filterItem){
				if("priority0".equals(item))
				  priorities.add(0);
				if("priority1".equals(item))
				  priorities.add(1);
				if("priority2".equals(item))
				  priorities.add(2);
				if("priority3".equals(item))
				  priorities.add(3);
				if("status0".equals(item))
				  statuses.add(0);
				if("status1".equals(item))
				  statuses.add(1);
				if("status2".equals(item))
				  statuses.add(2);			
			}
		}
		
	return storyDao.filterStrories(projectId,priorities,statuses);
	}


	 
	
}
