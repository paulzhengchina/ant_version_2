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

public class StoryTest extends TestCase{
   
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
	}
	
	@Test
	public void testCalculateStoryPoint(){
	//	storyService.calculateTotalPointsForProject(new ProjectTo(PROJECTID));
	}
	

}
