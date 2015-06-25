package com.createidea.scrumfriend.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.createidea.scrumfriend.dao.project.ProjectDao;
import com.createidea.scrumfriend.dao.user.UserDao;
import com.createidea.scrumfriend.service.project.ProjectService;
import com.createidea.scrumfriend.service.sprint.SprintService;
import com.createidea.scrumfriend.service.statistics.StatisticsService;
import com.createidea.scrumfriend.service.story.StoryService;
import com.createidea.scrumfriend.struts.action.ProjectAction;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StatisticsProjectTo;
import com.createidea.scrumfriend.to.UserTo;

public class Project extends TestCase{
   
	ProjectAction projectAction=null;
	ProjectService projectService=null;
	SprintService sprintService=null;
	StatisticsService statisticsService=null;
	StoryService storyService=null;
	ProjectDao projectDao=null;
	UserDao userDao=null;
	protected static final String PROJECTID="402883a33b1c4fca013b1c4fcce40001";
	BeanFactory factory=null;
	
	
	@Before
	public void setUp(){
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		projectDao=(ProjectDao)factory.getBean("projectDao");
		sprintService=(SprintService)factory.getBean("sprintService");
		storyService=(StoryService)factory.getBean("storyService");
		userDao=(UserDao)factory.getBean("userDao");
		statisticsService=(StatisticsService)factory.getBean("statisticsService");
		projectService=(ProjectService)factory.getBean("projectService");
		
	}
	
	@Test
	public void testProjectUsers(){		
		
		ProjectTo project=projectDao.getProjectById("402883a33b1c4fca013b1c4fcce40001");
		project.setName("test");
		UserTo user= userDao.getUserByName("admin");	
		project.getUsers().add(user);
		projectDao.updateProject(project);		
		
	}
	
	@Test
	public void testSetStatisticsForProject(){
		ProjectTo project=projectService.getProjectById(PROJECTID);
		List<SprintTo> sprints=sprintService.getSprintsForProject(PROJECTID);
		for( SprintTo sprint : sprints){
			statisticsService.createStatisticsForProject(sprint);
		}
	
	}
	
	@Test
	public void testCreateSprints(){
		List<SprintTo> sprints=new ArrayList();
		Calendar calendarStart=Calendar.getInstance();
		Calendar calendarEnd=Calendar.getInstance();
		Date startDate=calendarStart.getTime();
		calendarEnd.add(Calendar.WEEK_OF_YEAR, 2);
		Date endDate=calendarEnd.getTime();
		for(int i=1; i<20; i++){
			SprintTo sprint=new SprintTo();
			sprint.setStartTime(startDate);
			sprint.setEndTime(endDate);
			sprint.setName("HH");
		//	sprint.setSerial(i);
			sprint.setStatus("A");
			sprint.setProject(new ProjectTo(PROJECTID));
			calendarStart.add(Calendar.WEEK_OF_YEAR, 2);
			calendarEnd.add(Calendar.WEEK_OF_YEAR, 2);
			startDate=calendarStart.getTime();
			endDate=calendarEnd.getTime();
			sprintService.createSprint(sprint, PROJECTID,"0");
		}
		
	}
	
		
	public StatisticsProjectTo setUpStatisticsProject(SprintTo sprint){
		ProjectTo project=sprint.getProject();
	//	float totalStoryPoint=storyService.calculateTotalPointsForProject(project);
	//	float completedStoryPoint=storyService.calculateCompletedPointForProject(project);
	//	float remainingStoryPoint=storyService.calculateRemainingPointForProject(project);
		StatisticsProjectTo statistics=new StatisticsProjectTo();
		statistics.setDate(sprint.getEndTime());
		statistics.setProject(sprint.getProject());
		//statistics.setSprintSerial(sprint.getSerial());
	//	statistics.setCompletedStoryPoint(completedStoryPoint);
	//	statistics.setRemainingStoryPoint(remainingStoryPoint);
	//	statistics.setTotalStoryPoint(totalStoryPoint);
		return statistics;
	}
	
}
