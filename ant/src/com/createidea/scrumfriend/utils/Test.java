package com.createidea.scrumfriend.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File file = new File("src/com/createidea/scrumfriend/utils/email/content/template/update.html");
		//  File[] files = file.listFiles();
		  try {
			BufferedReader br = new BufferedReader(new FileReader("src/com/createidea/scrumfriend/utils/email/content/template/update.html"));
			System.out.println("ok");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println(file.getAbsolutePath()+files.length);
		//getFiles("./WebContent/WEB-INF/classes/com/createidea/scrumfriend/utils/email/content/template");
	}
	
	static void getFiles(String filePath){
		  File root = new File(filePath);
		  if(root.exists())
		  {
		  System.out.println("显示"+filePath+"下所有子目录及其文件"+root.getAbsolutePath());
		    File[] files = root.listFiles();
		    for(File file:files){    
		     if(file.isDirectory()){
		      /*
		       * 递归调用
		       */
		      getFiles(file.getAbsolutePath());
		      System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
		     }else{
		      System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
		     }    
		    }
		  }
		 }

}
