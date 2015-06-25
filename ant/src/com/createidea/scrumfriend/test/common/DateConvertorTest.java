package com.createidea.scrumfriend.test.common;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import com.createidea.scrumfriend.utils.DateConverter;

public class DateConvertorTest extends TestCase {

	
	@Test	
	public void testParseStringToDate(){
		DateConverter dateConver=new DateConverter();
		String dateString="2013-3-13";
		Date date=dateConver.parseStringToDate(dateString);
		assertNotNull(date);
		
	}
}
