package com.createidea.scrumfriend.utils.email;

import java.util.List;

import com.createidea.scrumfriend.to.UserTo;



public class SendMailService {
	
	private MailSenderInfo mailInfo = new MailSenderInfo();
    public  void prepareSender(){  
        mailInfo.setMailServerHost("smtp.exmail.qq.com");   
        mailInfo.setMailServerPort("25");   
        mailInfo.setValidate(true);   
        mailInfo.setUserName("noreply@antkanban.com");   
        mailInfo.setPassword("password2");//您的邮箱密码  
        mailInfo.setFromAddress("noreply@antkanban.com"); 
    }
    
    public void sender(String receiverAddress,String content,String title){
    	    prepareSender();
    	    mailInfo.setToAddress(receiverAddress);   
    	    mailInfo.setSubject(title);   
    	    mailInfo.setContent(content);   
    	       //这个类主要来发送邮件  
    	    SimpleMailSender sms = new SimpleMailSender();
    	    sms.sendTextMail(mailInfo);//发送文体格式   
    	   // sms.sendHtmlMail(mailInfo);//发送html格式  
    }
    
    public void sendWithHtmlContent(String receiverAddress,String htmlPath)
    {
    	prepareSender();
    	mailInfo.setToAddress(receiverAddress);    
    	SimpleMailSender sms = new SimpleMailSender();
    	sms.sendHtmlMail(mailInfo,htmlPath);
    }
}
