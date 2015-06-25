package com.createidea.scrumfriend.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class FileUtil
{
	public static void createFilePathIfNotExisted(String filePath)
	{
		File f = new File(filePath);
		if(f.isDirectory())
			f.mkdirs();
		
	}
	public static String getFileName(String fullFileName)
	{
		File f = new File(fullFileName);
		return f.getName();
	}
	public static String getFilePathWithoutName(String fullFileName)
	{
		File f = new File(fullFileName);
		return f.getParent();
	}
	public static void archiveFile(String srcFile, String archiveFold,String archiveFileName) throws Exception
	{
		createPath(archiveFold);
		String descFile = getFullFileName(archiveFold,archiveFileName);
		moveFile(srcFile,descFile);
	}
	public static String appendSuffix(String fileName,String suffix)
	{
		String newName = fileName.substring(0,fileName.lastIndexOf("."));
		return newName+"."+suffix;
	}
	public static void getFielContentAsBytes(String file,OutputStream os) throws Exception
	{
		File srcFile = new File(file);
		InputStream inPut = new FileInputStream(srcFile);
		byte[] bytes=new byte[1024];
        int i;
        while((i=inPut.read(bytes))!= -1){
            os.write(bytes,0,i);
        }
        inPut.close();
	}
    /**
     * This method is to upload files
     * @param FormFile srcFile, only the source file name.
     * @param String destPath, only the destination file path.
     * @throws Exception
     */
//    public static void uploadFile(File srcFile, String destPath) throws Exception {
//        try {
//            String fileName = srcFile.getFileName();
//            String destFile = destPath + File.separator + fileName;
//            File destDirectory = new File(destPath);
//            if(!destDirectory.isDirectory()){
//                destDirectory.mkdir();
//            }
//            InputStream inPut = srcFile.getInputStream();
//            FileOutputStream outPut = new FileOutputStream(destFile);
//            outPut.write(srcFile.getFileData());
//            outPut.flush();
//            inPut.close();
//            outPut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
    /**
     * This method is to copy file
     * @param String srcPath, must including the source file path and name.
     * @param String destPath, must including the destination file path and name.
     * @throws Exception
     */
    
    public static void uploadFile(File srcFile, String destPath,String filename) throws Exception {
        try {
           
            String destFile = destPath + File.separator + filename;
            File destDirectory = new File(destPath);
            if(!destDirectory.isDirectory()){
                destDirectory.mkdir();
            }
            
           
                FileInputStream inPut = new FileInputStream(srcFile);
                FileOutputStream outPut = new FileOutputStream(destFile);
                byte[] bytes=new byte[1024];
                int i;
                while((i=inPut.read(bytes))!= -1){
                    outPut.write(bytes,0,i);
                }
                inPut.close();
                outPut.close();
            } catch(Exception e){
                e.printStackTrace();
                throw e;
            }
    }
    
    public static void copyFile(String srcPath, String destPath)throws Exception {
        try{
            FileInputStream inPut = new FileInputStream(srcPath);
            FileOutputStream outPut = new FileOutputStream(destPath);
            byte[] bytes=new byte[1024];
            int i;
            while((i=inPut.read(bytes))!= -1){
                outPut.write(bytes,0,i);
            }
            inPut.close();
            outPut.close();
        } catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
     /**
      * This method is to delete file
      * @param String srcPath, must including the source file path and name.
      * @throws Exception
      */
     public static void deleteFile(String srcPath)throws Exception{
        try{
            File inPut = new File(srcPath);
            inPut.delete();
        }catch(Exception e){
        	e.printStackTrace();
            throw e;
        }
    }
    public static void deleteFileOnExit(String srcPath)throws Exception{
         try{
             File inPut = new File(srcPath);
             inPut.deleteOnExit();
         }catch(Exception e){
        	 e.printStackTrace();
             throw e;
         }
     }
    public static String getFullFileName(String filePath,String fileName)
    {
    	filePath = formatPath(filePath);
    	return filePath + fileName;
    }
    /**
     * This method is to move file from one folder to the other
     * @param String srcPath, must including the source file path and name.
     * @param String destPath, must including the destination file path and name.
     * @throws Exception
     */
    public static void moveFile(String srcPath, String destPath)throws Exception{
        copyFile(srcPath,destPath);
        deleteFile(srcPath);
    }
    
    public static void copyFiles(String srcPath, String destPath) throws IOException 
    {
    	File srcFile = new File(srcPath);
		srcFile.mkdirs();
		(new File(destPath)).mkdirs();
		File[] file = srcFile.listFiles();
		for (int i = 0; i < file.length; i++) 
		{
			if (file[i].isFile()) 
			{
				FileInputStream input = new FileInputStream(file[i]);
				FileOutputStream output = new FileOutputStream(destPath + "/" + file[i].getName());
				byte[] bytes = new byte[1024];
				int len;
				while ((len = input.read(bytes)) != -1)
					output.write(bytes,0,len);
				output.close();
				input.close();
			}
			if (file[i].isDirectory()) {
				copyFiles(srcPath+"/"+file[i].getName(),destPath+"/"+file[i].getName());
			}
		}
	}
    public static void deleteFiles(String path) 
    {
    	File file = new File(path);
		if (file.isDirectory()) {
			File[] child = file.listFiles();
			if (child != null && child.length != 0) {
				for (int i = 0; i < child.length; i++) {
					deleteFiles(child[i].getPath());
					child[i].delete();
				}
			}
		}
		file.delete();
	}
    public static void moveFiles(String srcPath, String destPath) throws Exception{
    	copyFiles(srcPath,destPath);
    	deleteFiles(srcPath);
    }

    /**
     * This method is to get the file size, represent as bytes.
     * @param String srcPath
     * @return long length of the file
     * @throws Exception
     */
    public static long getFileSize(String srcPath)throws Exception{
        try{
        File inputFile = new File(srcPath);
        return inputFile.length();
        }catch(Exception e){
        	e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method is to get the last modified date of the file
     * @param String srcPath
     * @return Date the last modified date of the file
     * @throws Exception
     */
    public static Date getFileLastModifiedTime(String srcPath)throws Exception{
        try{
        File inputFile = new File(srcPath);
        return new Date(inputFile.lastModified());
        }catch(Exception e){
        	e.printStackTrace();
            throw e;
        }
    }
    public static boolean existFile(String fullFileName)
    {
    	File f = new File(fullFileName);
    	return f.exists();
    }
    public static boolean createPath(String filePath)
    {
    	filePath = formatPath(filePath);
    	File dir = new File(filePath);
    	return dir.mkdirs();
    }
    public static String combineFilePath(String root,String subFolder)
    {
    	root = formatPath(root);
    	String fullPath = root + subFolder;
    	return formatPath(fullPath);
    }
    
    public static String getFirstFileFromFolder(String path)
    {
    	File fp = new File(path);
    	File f[] = fp.listFiles();
    	if(f!=null&&f.length>0)
    	{
    		for(int i=0;i<f.length;i++)
    		{
    			if(f[i].isFile())
    				return f[i].getPath();
    		}
    	}
    	return null;
    }
    /** index start with 1 ***/
    public static String getFileFromFolder(String path, int index)
    {
    	File fp = new File(path);
    	File f[] = fp.listFiles();
    	int starter = 1;
    	if(f!=null&&f.length>0)
    	{
    		for(int i=0;i<f.length;i++)
    		{
    			if(f[i].isFile())
    			{
    				if(starter == index)
    					return f[i].getPath();
    				else
    					starter++;
    			}
    				
    		}
    	}
    	return null;
    }

    public static int getFileCountFromFolderByPrefix(String path, String prefix){
        File fp = new File(path);
    	File f[] = fp.listFiles();
        int resultCount = 0;
        if(f!=null&&f.length>0){
    		for(int i=0;i<f.length;i++){
    			if(f[i].isFile()){
    				if( (f[i].getName().indexOf(prefix) > -1) && (f[i].getName().indexOf("inqueue") == -1) )
    					resultCount++;
    			}

    		}
    	}
        return resultCount;
    }

    public static String getFileFromFolderByPrefix(String path, String prefix){
        File fp = new File(path);
    	File f[] = fp.listFiles();
        if(f!=null&&f.length>0){
    		for(int i=0;i<f.length;i++){
    			if(f[i].isFile()){
    				if( (f[i].getName().indexOf(prefix) > -1) && (f[i].getName().indexOf("inqueue") == -1) )
                        return f[i].getPath();
                }

    		}
    	}
        return null;
    }

//    public static void main(String args[]) throws Exception
//    {
//    	moveFiles("E:\\Project\\CMS\\codes\\cms\\temp","E:\\Project\\CMS\\codes\\cms\\html");
////    	String list = getFilePathWithoutName("E:\\books\\open-source-tools-choice.pdf");
////    	System.out.println(list);
//    }
    
    public static List listFile(String path,FilenameFilter filter)
    {
    	File fp = new File(path);
    	File f[] = fp.listFiles(filter);
    	List list = new ArrayList();
    	if(f != null && f.length > 0)
    	{
    		for(int i=0;i<f.length;i++)
    		{
    			list.add(f[i]);
    		}
    	}
    	return list;
    }
    
    public static String formatPath(String path)
    {
    	if(StringUtil.isNotEmpty(path)){
        	path = path.replaceAll("\\\\", "/");
        	if(!path.endsWith("/"))
        		path = path + "/";
        	return path;
    	}else{
    		return "";
    	}
    }
    public static String formatFullName(String fullName)
    {
    	return fullName.replaceAll("\\\\", "/");
    }
}
