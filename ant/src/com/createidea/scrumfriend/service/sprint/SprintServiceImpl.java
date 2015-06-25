package com.createidea.scrumfriend.service.sprint;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.createidea.scrumfriend.dao.sprint.SprintDao;
import com.createidea.scrumfriend.dao.story.StoryDao;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.TreeNodeTo;

public class SprintServiceImpl implements SprintService {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SprintDao sprintDao;
	private StoryDao storyDao;

	public SprintDao getSprintDao() {
		return sprintDao;
	}

	public void setSprintDao(SprintDao sprintDao) {
		this.sprintDao = sprintDao;
	}
    
	public StoryDao getStoryDao() {
		return storyDao;
	}

	public void setStoryDao(StoryDao storyDao) {
		this.storyDao = storyDao;
	}

	

	@Override
	public SprintTo updateSprint(SprintTo sprint) {
		if("".equals(sprint.getParentSprint().getId()))
			sprint.setParentSprint(null);
		sprint.setSubSprints(sprintDao.getSprintById(sprint.getId()).getSubSprints());
	    sprintDao.updateSprint(sprint);	    
		return sprint;
		
	}


	@Override
	public SprintTo createSprint(SprintTo sprint, String projectId ,String parentSprintId) {
		
		if(parentSprintId!=null&&parentSprintId.length()>30){
			SprintTo parentSprintTo=new SprintTo();
			parentSprintTo.setId(parentSprintId);
			sprint.setParentSprint(parentSprintTo);
		}
		return sprintDao.createSprint(sprint,projectId);
		
	}

	@Override
	public List<SprintTo> getSprintsForProject(String projectId) {
		return sprintDao.getSprintForProject(projectId);
		
	}

	@Override
	public void deleteSprint(String sprintId) {
		sprintDao.deleteSprint(sprintId);
		
	}

	@Override
	public SprintTo getSprintById(String sprintId) {
		return sprintDao.getSprintById(sprintId);
	}

	
	@Override
	public SprintTo getCurrentSprint(String projectId) {
		// TODO Auto-generated method stub
		List<SprintTo> allCurrentSprints=sprintDao.getCurrentSprints(projectId);
		return findCurrentChildSprint(allCurrentSprints);
	}
	
	private SprintTo findCurrentChildSprint(List<SprintTo> sprints)
	{
		List<SprintTo> parentSprints=new ArrayList<SprintTo>();
		if(sprints==null&&sprints.size()<1)
			return null;
		
		for(SprintTo sprint :sprints ){
			if(sprint.getSubSprints()!=null&&sprint.getSubSprints().size()>0)
				parentSprints.add(sprint);
			}
		sprints.removeAll(parentSprints);
		if(sprints.size()<=0)
			return null;
		
		return sprints.get(0);
		}
		

	@Override
	public List<SprintTo> getParentSprints(String projectId) {
		// TODO Auto-generated method stub
		return sprintDao.getParentSprints(projectId);
	}

	  
}
