package com.createidea.scrumfriend.test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date currentTime = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String dateString = formatter.format(currentTime);  
		ParsePosition pos = new ParsePosition(0);  
		Date currentTime_2 = formatter.parse(dateString, pos);  
		System.out.println(currentTime_2); 

	}

}
