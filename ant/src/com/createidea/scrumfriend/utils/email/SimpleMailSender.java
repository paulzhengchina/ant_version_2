package com.createidea.scrumfriend.utils.email;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class SimpleMailSender {
	/**  
	  * 以文本格式发送邮件  
	  * @param mailInfo 待发送的邮件的信息  
	  */   
	    public boolean sendTextMail(MailSenderInfo mailInfo) {   
	      // 判断是否需要身份认证   
	      MyAuthenticator authenticator = null;   
	      Properties pro = mailInfo.getProperties();  
	      if (mailInfo.isValidate()) {   
	      // 如果需要身份认证，则创建一个密码验证器   
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
	      }  
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
	      Session sendMailSession = Session.getInstance(pro);
	      sendMailSession.setDebug(true); 
	      try {   
	      // 根据session创建一个邮件消息   
	      Message mailMessage = new MimeMessage(sendMailSession);   
	      // 创建邮件发送者地址   
	      Address from = new InternetAddress(mailInfo.getFromAddress()); 
	      
	      // 设置邮件消息的发送者   
	      mailMessage.setFrom(from);   
	      // 创建邮件的接收者地址，并设置到邮件消息中   
	      Address to = new InternetAddress(mailInfo.getToAddress());   
	      mailMessage.setRecipient(Message.RecipientType.TO,to);   
	      // 设置邮件消息的主题   
	      mailMessage.setSubject(mailInfo.getSubject());   
	      // 设置邮件消息发送的时间   
	      mailMessage.setSentDate(new Date());   
	      // 设置邮件消息的主要内容   
	      String mailContent = mailInfo.getContent();   
	      mailMessage.setText(mailContent);   
	      // 发送邮件   
	      Transport transport = sendMailSession.getTransport();// 发送用户名、密码连接到指定的 smtp 服务器  
	      transport.connect("smtp.exmail.qq.com", "noreply@antkanban.com", "password2");
	      transport.sendMessage(mailMessage, mailMessage.getRecipients(Message.RecipientType.TO));
	      transport.close();    
	      return true;   
	      } catch (MessagingException ex) {   
	          ex.printStackTrace();   
	      }   
	      return false;   
	    }   
	      
	    /**  
	      * 以HTML格式发送邮件  
	      * @param mailInfo 待发送的邮件信息  
	      */ 
	    public static String chinese(String a) {   
	    	try {    return new String(a.getBytes("ISO-8859-1"));   } 
	    	catch (UnsupportedEncodingException ex)
	    	{    return null;   } 
	    }
       
	    public  boolean sendHtmlMail(MailSenderInfo mailInfo,String rootpath){   
	      // 判断是否需要身份认证   
	      MyAuthenticator authenticator = null;  
	      Properties pro = mailInfo.getProperties();  
	      //如果需要身份认证，则创建一个密码验证器    
	      if (mailInfo.isValidate()) {   
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());  
	      }   
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
	      Session sendMailSession = Session.getInstance(pro);   
	      try {   
	      // 根据session创建一个邮件消息   
		    MimeMessage mailMessage = new MimeMessage(sendMailSession); 
		    mailMessage.setSubject(chinese("Ant_Kanban"), "ISO-8859-1");
		    
	      
	      // 创建邮件发送者地址   
	      Address from = new InternetAddress(mailInfo.getFromAddress());   
	      // 设置邮件消息的发送者   
	      mailMessage.setFrom(from);   
	      // 创建邮件的接收者地址，并设置到邮件消息中   
	      Address to = new InternetAddress(mailInfo.getToAddress());   
	      // Message.RecipientType.TO属性表示接收者的类型为TO   
	      mailMessage.setRecipient(Message.RecipientType.TO,to);   
	      // 设置邮件消息的主题   
	      mailMessage.setSubject(mailInfo.getSubject()); 
	     
	      // 设置邮件消息发送的时间   
	      mailMessage.setSentDate(new Date());   
	   
	      // 将MiniMultipart对象设置为邮件内容   
	      mailMessage.setContent("<div style='background:green'>test</div>","text/html;charset=gb2312");
	      mailMessage.saveChanges();
	      // 发送邮件   
	      Transport transport = sendMailSession.getTransport();// 发送用户名、密码连接到指定的 smtp 服务器  
	      transport.connect("smtp.exmail.qq.com", "noreply@antkanban.com", "password2");
	      transport.sendMessage(mailMessage, mailMessage.getRecipients(Message.RecipientType.TO));
	      transport.close();    
	      return true;   
	      } catch (MessagingException ex) {   
	          ex.printStackTrace();   
	      }   
	      return false;   
	    }  
	    
	private String getEmailContent(String infoContent){
		StringBuffer content=new StringBuffer();
		content.append("<div class='content' style='margin:0 auto;width:600px;height:auto;border:1px solid black'>")
				    .append("<div class='slogan' style='margin-top:20px;height:150px;border:1px solid black'>")
				    .append("</div>")
				    
				    .append("<div class='info' style='margin-top:20px;height:300px;border:1px solid black'>")
				      .append(infoContent)
				    .append("</div>")
				    
				    .append("<div class='foot' style='margin-top:20px;height:100px;border:1px solid black'>")
				    .append("</div>")
				.append("</div>");
		return content.toString();
	}
	
	public MimeBodyPart prepareImgs(String imgPath,String imgId)
	{
		MimeBodyPart jpgBody = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(imgPath);
		try {
			jpgBody.setDataHandler(new DataHandler(fds));
			jpgBody.setContentID(imgId);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return jpgBody;
	}
	


	/**
	 * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分
	 */
	public MimeBodyPart createContent(String body, String fileName)
			throws Exception {
		// 用于保存最终正文部分
		MimeBodyPart contentBody = new MimeBodyPart();
		// 用于组合文本和图片，"related"型的MimeMultipart对象
		MimeMultipart contentMulti = new MimeMultipart("related");

		// 正文的文本部分
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(body, "text/html;charset=gbk");
		contentMulti.addBodyPart(textBody);

		// 正文的图片部分
		MimeBodyPart jpgBody = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(fileName);
		jpgBody.setDataHandler(new DataHandler(fds));
		jpgBody.setContentID("logo_jpg");
		contentMulti.addBodyPart(jpgBody);

		// 将上面"related"型的 MimeMultipart 对象作为邮件的正文
		contentBody.setContent(contentMulti);
		return contentBody;
	}

	/**
	 * 根据传入的 Seesion 对象创建混合型的 MIME消息
	 */
//	public MimeMessage createMessage(Session session) throws Exception {
//		String from = "test_hao@163.com";
//		String to = "test_hao@sina.cn";
//		String subject = "创建内含附件、图文并茂的邮件！";
//		String body = "<h4>内含附件、图文并茂的邮件测试！！！</h4> </br>"
//				+ "<a href = http://haolloyin.blog.51cto.com/> 蚂蚁</a></br>"
//				+ "<img src = \"cid:logo_jpg\"></a>";
//
//		MimeMessage msg = new MimeMessage(session);
//		msg.setFrom(new InternetAddress(from));
//		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		msg.setSubject(subject);
//
//		// 创建邮件的各个 MimeBodyPart 部分
//		MimeBodyPart attachment01 = createAttachment("F:\\java\\Snake.java");
//		MimeBodyPart attachment02 = createAttachment("F:\\java\\meng.mp3");
//		MimeBodyPart content = createContent(body, "F:\\java\\logo.jpg");
//
//		// 将邮件中各个部分组合到一个"mixed"型的 MimeMultipart 对象
//		MimeMultipart allPart = new MimeMultipart("mixed");
//		allPart.addBodyPart(attachment01);
//		allPart.addBodyPart(attachment02);
//		allPart.addBodyPart(content);
//
//		// 将上面混合型的 MimeMultipart 对象作为邮件内容并保存
//		msg.setContent(allPart);
//		msg.saveChanges();
//		return msg;
//	}

}
