package com.createidea.scrumfriend.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImage {
   public static void saveImage(File file,String destination){
	   FileInputStream fileinputStream;
	   FileOutputStream outputStream;
	   File storeFile=new File(destination+file.getName());
	   try {
		fileinputStream=new FileInputStream(file);
		outputStream=new FileOutputStream(storeFile);
		byte [] b=new byte[100];
		while(fileinputStream.read(b)!=0){
			outputStream.write(b);
		}
		fileinputStream.close();
		outputStream.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		
	}
   }
}
