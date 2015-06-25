package com.createidea.scrumfriend.test;

import java.sql.Date;
import java.util.Map;

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
import com.createidea.scrumfriend.to.SprintTo;

public class Statistics extends TestCase {
	ProjectAction projectAction=null;
	ProjectService projectService=null;
	SprintService sprintService=null;
	StatisticsService statisticsService=null;
	StoryService storyService=null;
	ProjectDao projectDao=null;
	UserDao userDao=null;
	protected static final String PROJECTID="402883c93d438107013d4384db9b0004";
	BeanFactory factory=null;
	SprintTo sprint=null;
	
	
	@Before
	public void setUp(){
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		projectDao=(ProjectDao)factory.getBean("projectDao");
		sprintService=(SprintService)factory.getBean("sprintService");
		statisticsService=(StatisticsService)factory.getBean("statisticsService");
		storyService=(StoryService)factory.getBean("storyService");
		userDao=(UserDao)factory.getBean("userDao");
	//	sprint=sprintService.getSprintsForProject(PROJECTID).get(0);
	}
	
//	@Test
//	public void testSetStaitisticsForSprintWhenStoryStatusChanged(){
//		statisticsService.updateStatisticsForSprint(sprint);		
//	}
	
//	@Test
//	public void testGetSprints(){
//		statisticsService.updateStatisticsForAllProjects();
//		System.out.println("ba........................................");
//	}
	
	@Test
	public void testCalculateStoryPoints()
	{
		Map map=statisticsService.getStatisticsForStoryPriorities(PROJECTID);
		Object object=map.get("tst");
		System.out.println("ba........................................"+object.toString());
	}
//	public float calculateCommittedStoryPoint(){
//		return statisticsService.calculateCommittedStoryPoint(sprint);
//	}
//	
//	public float calculateCompletedStoryPoint(){
//		return statisticsService.calculateCompletedStoryPoint(sprint);
//	}
//	
//	public float calculateTotalEffortForSprint(){
//		return statisticsService.calculateTotalEffortForSprint(sprint);
//	}
//	
//	public float calculateRemainingEffortForSprint(){
//		return statisticsService.calculateRemainingEffortForSprint(sprint);
//	}
}
