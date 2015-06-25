package com.createidea.scrumfriend.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	 

	    private static String baseDir = "/upload/"; // 上传文件存储目录

	    private static String fileExt = "jpg,jpeg,bmp,gif,png";

	    private static Long maxSize = 0l;

	    private static String dirType = "1"; // 0:�?建目录 1:按天存入目录 2:按月存入目录 3:按扩展�??存目录 建议使用按天存


	 

	    public void init() throws ServletException {

	        baseDir = this.getInitParameter("baseDir"); //获�?�web.xml中servlet的�?置文件目录�?�数

	        if (StringUtils.isEmpty(baseDir)) baseDir = "/upload/"; //获�?�文件上传存储的相当路径


	        String realBaseDir = this.getServletConfig().getServletContext().getRealPath(baseDir);

	        File baseFile = new File(realBaseDir);

	        if (!baseFile.exists()) {

	            baseFile.mkdir();

	        }


	        fileExt = this.getInitParameter("fileExt"); //获�?�文件类型�?�数

	        if (StringUtils.isEmpty(fileExt)) fileExt = "jpg,jpeg,gif,bmp,png";


	        String maxSize_str = this.getInitParameter("maxSize"); //获�?�文件大�?�?�数

	        if (StringUtils.isNotEmpty(maxSize_str)) {

	            maxSize = new Long(maxSize_str);

	        } else {

	            maxSize = Long.valueOf("5242880"); //5M

	        }


	        dirType = this.getInitParameter("dirType"); //获�?�文件目录类型�?�数


	        if (StringUtils.isEmpty(dirType)) dirType = "1";

	        if (",0,1,2,3,".indexOf("," + dirType + ",") < 0) dirType = "1";

	    }


	 

	    // 上传文件数�?�处�?�过程

	 

	    @SuppressWarnings( { "unchecked" })

	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        response.setContentType("text/html; charset=UTF-8");

	        response.setHeader("Cache-Control", "no-cache");


	        String err = "";

	        String newFileName = "";

	 

	        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传

	            try {

	                String dispoString = request.getHeader("Content-Disposition");

	                int iFindStart = dispoString.indexOf("name=\"")+6;

	                int iFindEnd = dispoString.indexOf("\"", iFindStart);

	 

	                iFindStart = dispoString.indexOf("filename=\"")+10;

	                iFindEnd = dispoString.indexOf("\"", iFindStart);

	                String sFileName = dispoString.substring(iFindStart, iFindEnd);

	 

	                int i = request.getContentLength();

	                byte buffer[] = new byte[i];

	                int j = 0;

	                while(j < i) { //获�?�表�?�的上传文件

	                    int k = request.getInputStream().read(buffer, j, i-j);

	                    j += k;

	                }

	 

	                if (buffer.length == 0) { //文件是�?�为空

	                    printInfo(response, "上传文件�?能为空", "");

	                    return;

	                }

	                if (maxSize > 0 && buffer.length > maxSize) { //检查文件大�?

	                    printInfo(response, "上传文件的大�?超出�?制", "");

	                    return;

	                }

	 

	                String filepathString = getSaveFilePath(sFileName, response);

	                if ("�?�?许上传此类型的文件".equals(filepathString)) return; //检查文件类型

	 

	 

	                OutputStream out=new BufferedOutputStream(new FileOutputStream(this.getServletConfig().getServletContext().getRealPath("") + filepathString,true));

	                out.write(buffer);

	                out.close();

	 

	                newFileName = request.getContextPath() + filepathString;

	            } catch (Exception ex) {

	                System.out.println(ex.getMessage());

	                newFileName = "";

	                err = "错误: " + ex.getMessage();

	            }

	        } else {

	            DiskFileUpload upload = new DiskFileUpload();

	 

	            try {

	                List<FileItem> items = upload.parseRequest(request);

	                Map<String, Serializable> fields = new HashMap<String, Serializable>();

	                Iterator<FileItem> iter = items.iterator();

	                while (iter.hasNext()) {

	                    FileItem item = (FileItem) iter.next();

	                    if (item.isFormField())

	                        fields.put(item.getFieldName(), item.getString());

	                    else

	                        fields.put(item.getFieldName(), item);

	                }

	                FileItem uploadFile = (FileItem) fields.get("filedata"); //获�?�表�?�的上传文件

	                String fileNameLong = uploadFile.getName(); //获�?�文件上传路径�??称


	                if (uploadFile.getSize() == 0) { //文件是�?�为空

	                    printInfo(response, "上传文件�?能为空", "");

	                    return;

	                }

	                if (maxSize > 0 && uploadFile.getSize() > maxSize) { //检查文件大�?

	                    printInfo(response, "上传文件的大�?超出�?制", "");

	                    return;

	                }

	 

	                String filepathString = getSaveFilePath(fileNameLong, response);

	                if ("�?�?许上传此类型的文件".equals(filepathString)) return; //检查文件类型

	 

	                File savefile = new File(this.getServletConfig().getServletContext().getRealPath("") + filepathString);

	                uploadFile.write(savefile); //存储上传文件


	                newFileName = request.getContextPath() + filepathString;

	            } catch (Exception ex) {

	                System.out.println(ex.getMessage());

	                newFileName = "";

	                err = "错误: " + ex.getMessage();

	            }

	        }

	        printInfo(response, err, newFileName);

	    }

	 

	    public String getSaveFilePath(String sFileName, HttpServletResponse response) throws IOException {

	        String extensionName = sFileName.substring(sFileName.lastIndexOf(".") + 1); //获�?�文件扩展�??

	 

	        if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0) { //检查文件类型

	        printInfo(response, "�?�?许上传此类型的文件", "");

	        return "�?�?许上传此类型的文件";

	        }

	 

	        String fileFolder = ""; // 0:�?建目录, 1:按天存入目录, 2:按月存入目录, 3:按扩展�??存目录.建议使用按天存。

	        if (dirType.equalsIgnoreCase("1")) fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());

	        if (dirType.equalsIgnoreCase("2")) fileFolder = new SimpleDateFormat("yyyyMM").format(new Date());

	        if (dirType.equalsIgnoreCase("3")) fileFolder = extensionName.toLowerCase();


	        String saveDirPath = baseDir + fileFolder + "/"; //文件存储的相对路径

	        String saveFilePath = this.getServletConfig().getServletContext().getRealPath("") + saveDirPath; //文件存储在容器中的�?对路径


	        File fileDir = new File(saveFilePath); //构建文件目录以�?�目录文件

	        if (!fileDir.exists()) {

	            fileDir.mkdirs();

	        }

	        String filename = UUID.randomUUID().toString(); //�?命�??文件

	 

	        return saveDirPath + filename + "." + extensionName;

	    }


	 

	    // 使用I/O�?输出 json格�?的数�?�

	 

	    public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {

	        response.setContentType("text/plain");

	        response.setCharacterEncoding("UTF-8");

	        PrintWriter out = response.getWriter();

	        out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");

	        out.flush();

	        out.close();

	    }
}

