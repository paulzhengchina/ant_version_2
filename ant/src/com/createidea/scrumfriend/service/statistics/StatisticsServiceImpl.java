package com.createidea.scrumfriend.service.statistics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.createidea.scrumfriend.dao.sprint.SprintDao;
import com.createidea.scrumfriend.dao.statistics.StatisticsDao;
import com.createidea.scrumfriend.dao.story.StoryDao;
import com.createidea.scrumfriend.dao.task.TaskDao;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StatisticsProjectTo;
import com.createidea.scrumfriend.to.StatisticsSprintTo;

public class StatisticsServiceImpl implements StatisticsService {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private StatisticsDao statisticsDao;
    private StoryDao storyDao;
    private TaskDao taskDao;
    private SprintDao sprintDao;
  
	@Override
	public void createStatisticsForProject(SprintTo sprint) {
		// TODO Auto-generated method stub
		statisticsDao.saveStatistics(setUpStatisticsProject(sprint));
	}
	
	public StatisticsProjectTo setUpStatisticsProject(SprintTo sprint){
		ProjectTo project=sprint.getProject();
		float totalStoryPoint=storyDao.calculateTotalPointsForProject(project);
		float completedStoryPoint=storyDao.calculateCompletedPointForProject(project);
		float remainingStoryPoint=storyDao.calculateRemainingPointForProject(project);
		StatisticsProjectTo statistics=new StatisticsProjectTo();
		statistics.setDate(sprint.getEndTime());
		statistics.setProject(sprint.getProject());
	//	statistics.setSprintSerial(sprint.getSerial());
		statistics.setCompletedStoryPoint(completedStoryPoint);
		statistics.setRemainingStoryPoint(remainingStoryPoint);
		statistics.setTotalStoryPoint(totalStoryPoint);
		statisticsDao.saveStatistics(statistics);
		return statistics;
	}
	
    
    public  HashMap createDataForProjectBurnDown(String projectId) { 
    	HashMap dataMap=new HashMap<>();
	    List<StatisticsProjectTo> statistics=statisticsDao.getProjectStatistics(projectId);
	    int length=0;
	    if(statistics!=null&& (length=statistics.size())>0){
	    	float[] totalPointOfPoject=new float[length];
	    	float[] remainingPointOfProject=new float[length];
	    	String[] sprintsEndDate=new String[length];
		    for(int i=0; i<statistics.size();i++){
		    	totalPointOfPoject[i]=statistics.get(i).getTotalStoryPoint();
		    	remainingPointOfProject[i]=statistics.get(i).getRemainingStoryPoint();
		    	sprintsEndDate[i]=dateFormat.format(statistics.get(i).getDate());
		    }
		    dataMap.put("remainingPointOfProject", remainingPointOfProject);
		    dataMap.put("totalPointOfPoject", totalPointOfPoject);
		    dataMap.put("sprintsEndDate", sprintsEndDate);
	    }
	   return dataMap;
    }  
    
	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}
	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	@Override
	public List<StatisticsProjectTo> getProjectStatistics(String projectId) {
		// TODO Auto-generated method stub
		return statisticsDao.getProjectStatistics(projectId);
	}


	@Override
	public float calculateCommittedStoryPoint(SprintTo sprint) {
		// TODO Auto-generated method stub
		return storyDao.calculateCommittedStoryPoint(sprint.getId());
	}

	@Override
	public float calculateCompletedStoryPoint(SprintTo sprint) {
		// TODO Auto-generated method stub
		return storyDao.calculateCompletedStoryPoint(sprint.getId());
	}

	@Override
	public float calculateTotalEffortForSprint(SprintTo sprint) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float calculateRemainingEffortForSprint(SprintTo sprint) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateStatisticsForSprint(SprintTo sprint) {
		// TODO Auto-generated method stub
		StatisticsSprintTo statistics=sprint.getStatistics();
		if(statistics==null){
			statistics=new StatisticsSprintTo();
			statistics.setSprint(sprint);
		}
		//statistics.setCommittedStoryPoint(calculateCommittedStoryPoint(sprint));
		statistics.setCompletedStoryPoint(calculateCompletedStoryPoint(sprint));
		//statistics.setTotalEffort(calculateTotalEffortForSprint(sprint));
		//statistics.setRemainingEffort(calculateRemainingEffortForSprint(sprint));
		//statistics.setRemainingStoryPoint(statistics.getCommittedStoryPoint()-statistics.getCompletedStoryPoint());
		statisticsDao.updateSprintStatistics(statistics);
	}

	@Override
	public Map getDataForTeamVelocity(String projectId) {
		// TODO Auto-generated method stub
		HashMap dataMap=new HashMap<>();
	    List<StatisticsSprintTo> statistics=statisticsDao.getSprintsStatisticsForProject(projectId);
	    int length=0;
	    if(statistics!=null&& (length=statistics.size())>0){
	    	float[] completedPointOfSprints=new float[length];
	    	String[] sprints=new String[length];
		    for(int i=0; i<statistics.size();i++){
		    	completedPointOfSprints[i]=statistics.get(i).getCompletedStoryPoint();
		    	sprints[i]=dateFormat.format(statistics.get(i).getSprint().getEndTime());
		    }
		    dataMap.put("completedPointOfSprints", completedPointOfSprints);
		    dataMap.put("sprints", sprints);
	    }
		    return dataMap;
		    
	}
	
	public float calculateRemainingEffort(SprintTo sprint){
		return taskDao.calculateRemainingEffortForSprint(sprint);
	}

	public TaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	
	
   public void updateStatisticsForAllProjects(){
	    List<SprintTo> sprintsFinishedYesterday;
		sprintsFinishedYesterday = sprintDao.getSprintsFinishedYesterday(getEarlistTimeOfToday(),getTwoDaysAgo());
		for(SprintTo sprint:sprintsFinishedYesterday){
			setUpStatisticsProject(sprint);
			updateStatisticsForSprint(sprint);
		}
		
   }

	public SprintDao getSprintDao() {
		return sprintDao;
	}
	
	public void setSprintDao(SprintDao sprintDao) {
		this.sprintDao = sprintDao;
	}
    
	public Date getEarlistTimeOfToday(){
		Calendar time=Calendar.getInstance();
		time.set(Calendar.HOUR, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.SECOND, 0);
		return time.getTime();
	}
    public Date getTwoDaysAgo(){
       Calendar time=Calendar.getInstance();
 	   Calendar yesterdayTimeCalendar=time;
 	   int today=time.get(Calendar.DATE);
 	   int month=time.get(Calendar.MONTH);
 	   
 	   if(today==2){
 		   if(month!=1){
 			   yesterdayTimeCalendar.set(Calendar.MONTH, month-1);
 			   int days=yesterdayTimeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
 			   yesterdayTimeCalendar.set(Calendar.DATE, days);
 		   }
 		   else{
 			   yesterdayTimeCalendar.clear();
 			   yesterdayTimeCalendar.set(Calendar.YEAR, time.get(Calendar.YEAR-1));
 			   yesterdayTimeCalendar.set(Calendar.DATE, 31);
 			   yesterdayTimeCalendar.set(Calendar.MONTH, 12);			   
 		   }		   
 	   }
 	   
 	   else if(today==1){
		   if(month!=1){
			   yesterdayTimeCalendar.set(Calendar.MONTH, month-1);
			   int days=yesterdayTimeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			   yesterdayTimeCalendar.set(Calendar.DATE, days-1);
		   }
		   else{
			   yesterdayTimeCalendar.clear();
			   yesterdayTimeCalendar.set(Calendar.YEAR, time.get(Calendar.YEAR-1));
			   yesterdayTimeCalendar.set(Calendar.DATE, 30);
			   yesterdayTimeCalendar.set(Calendar.MONTH, 12);			   
		   }		   
	   }
 	   
 	   else{
 		  yesterdayTimeCalendar.add(Calendar.DATE, -2);
 	   }
 	  yesterdayTimeCalendar.set(Calendar.HOUR_OF_DAY, 23);
 	  yesterdayTimeCalendar.set(Calendar.MINUTE, 59);
 	  yesterdayTimeCalendar.set(Calendar.SECOND, 59);
 	  
 	   return yesterdayTimeCalendar.getTime();
    }

	

	public StoryDao getStoryDao() {
		return storyDao;
	}

	public void setStoryDao(StoryDao storyDao) {
		this.storyDao = storyDao;
	}

	@Override
	public Map<String, Integer> getStatisticsForStoryPriorities(String projectId) {
		// TODO Auto-generated method stub
		Map map = new HashMap<String,Integer>();
		map.put("tst", storyDao.calculateStoriesTotalPointByPriority(0,projectId));
		return map;
	}

	@Override
	public Map<String, Integer> getStatisticsForStoryStatuses(String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
