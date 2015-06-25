package com.createidea.scrumfriend.utils;

import java.util.ArrayList;
import java.util.List;

public class Pager {
	
   private int totalNum;
   private int totalPages;
   private List pageNums;
   private static final int numForEachPage=10;
   private int currentPage;
   
 
public Pager() {
	super();

}

public int getTotalNum() {
	return totalNum;
}
public void setTotalNum(int totalNum) {
	this.totalNum = totalNum;
}
public int getTotalPages() {
	return totalPages;
}
public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
}
public List getPageNums() {
	List nums=new ArrayList();
	if(totalNum>0){
		int num=0;
		if(totalNum%numForEachPage==0)
		    num=(int)totalNum/numForEachPage;
		else
			num=(int)totalNum/numForEachPage+1;	
		for(int i=1;i<num+1;i++){
			nums.add(i);
		}
	}
	return nums;
}
public void setPageNums(List pageNums) {
	this.pageNums = pageNums;
}
public int getNumForEachPage() {
	return numForEachPage;
}

public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return "currentPage : "+this.currentPage+".."+"totalNum : "+this.totalNum+".."+"totalPages : "+this.totalPages+"";
}
   
   
}
