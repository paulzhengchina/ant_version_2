package com.createidea.scrumfriend.test;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import antlr.collections.impl.LList;

public class Test {
    private double interestRate=0.0438;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       ArrayList<Integer> list=new ArrayList<>();
       list.add(1);
       list.add(2);
       list.add(3);
       for(Integer integer : list){
    	   list.remove(1);
       }
     
	}
	
	public double calculateRepayMoneyForSameInterest(double loanedMoney,int duration){
		return loanedMoney*(interestRate/12)+(loanedMoney*(interestRate/12)) /(Math.pow((1+(interestRate/12)), duration) -1);
	}
	
	public double calculateRepayInterestForSameInterest(double remainingMoney){
		return remainingMoney*(interestRate/12);
	}
	
	public double calculateRepayCapitalForSameInterest(double loanedMoney,double remainingMoney,int duration){
		return (calculateRepayMoneyForSameInterest(loanedMoney,duration)-calculateRepayInterestForSameInterest(remainingMoney));
	}
    
	
}
