package com.createidea.scrumfriend.utils.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MailTest {
	public static void main(String[] args){  
        //这个类主要是设置邮件  
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.exmail.qq.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("noreply@antkanban.com");   
     mailInfo.setPassword("password2");//您的邮箱密码  
     mailInfo.setFromAddress("noreply@antkanban.com");   
     mailInfo.setToAddress("531236618@qq.com");   
     mailInfo.setSubject("设置邮箱标题");   
     mailInfo.setContent("设置邮箱内容");   
        //这个类主要来发送邮件  
     
     SimpleMailSender sms = new SimpleMailSender();  
   }  
	
	public String readHTML(String spath) {

	    InputStreamReader isReader = null;

	    BufferedReader bufReader = null;

	    StringBuffer buf = new StringBuffer();

	    try {

	         File file = new File(spath);

	         isReader = new InputStreamReader(new FileInputStream(file), "utf-8");

	         bufReader = new BufferedReader(isReader, 1);

	         String data;

//	         while((data == bufReader.readLine())  != true) {
//
//	                buf.append(data);
//
//	         }

	    } catch (Exception e) {

	    //TODO 处理异常

	    } finally {

	      //TODO 关闭流

	     //  isReader.close();

	     // bufReader.close();

	    }

	    return buf.toString();

	}
	
	public void sendHTML() {

	 //   BodyPart html = new MimeBodyPart();

	   //生成的模板文件，注意此html模板文件的字符编码，请使用和红色部分一致的编码，否则收到的邮件会乱码

	   String mailTemplete = "./mailTemplete.html";

	    String readHTML = readHTML(mailTemplete);

	//   html.setContent(readHTML, "text/html; charset=utf-8");

	}
}
