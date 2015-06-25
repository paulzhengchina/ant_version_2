package com.createidea.scrumfriend.test;

public class TestRandomPassword {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
	      int password=(int)(Math.random()*1000000);
	      if(password<=99999)
	    	  password=999999-password;
	      System.out.println(password);
		}
	}

}
