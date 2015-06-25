package com.createidea.scrumfriend.test;

import java.util.Calendar;
import java.util.Date;



public class TestCronTask {
   public static void main(String[] args){
	   getTwoDaysAgo();
	   System.out.println(getYesterDay());
   }
   
   public static Date getTwoDaysAgo(){
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

   
   public static Date getYesterDay(){
       Calendar time=Calendar.getInstance();
 	   Calendar yesterdayTimeCalendar=time;
 	   int today=time.get(Calendar.DATE);
 	   int month=time.get(Calendar.MONTH);
 	   
 	   if(today==1){
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
 	   else{
 		  yesterdayTimeCalendar.set(Calendar.DATE, today-1);
 	   }
 	   return yesterdayTimeCalendar.getTime();
    }
}
