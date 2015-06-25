package com.createidea.scrumfriend.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.createidea.scrumfriend.dao.sprint.SprintDaoImpl;

public class SprintTest extends TestCase {
	
	SprintDaoImpl sprintDao=new SprintDaoImpl();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SprintTest().AddADay();
	}
	
	public void TestTimeSpan(){
	//	Date earlistTimeOfDay=sprintDao.getPureDateOfToday();
	//	Date latestTimeOfDay=sprintDao.getEarlistTimeOfToday();
	//	assertTrue(latestTimeOfDay.after(new Date()));
	}
	
	public void AddADay(){
		Calendar time = Calendar.getInstance();
		time.set(Calendar.MONTH, 7);
		time.set(Calendar.DATE, 0);
		time.set(Calendar.DATE,time.get(Calendar.DATE)-1);
		Date date= time.getTime();
		System.out.println(date.toString());
	}

}
