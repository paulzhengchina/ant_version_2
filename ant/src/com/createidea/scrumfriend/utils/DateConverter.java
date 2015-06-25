package com.createidea.scrumfriend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.XWorkException;


public class DateConverter extends StrutsTypeConverter {  
    
    public Object convertFromString(Map map, String as[], Class class1) {  
        return parseStringToDate(as[0]);  
    }  
  
    public String convertToString(Map map, Object obj) {  
        String s = null;  
        if (obj instanceof Date)  
            s = dateTimeFormat.format(obj);  
        return s;  
    }  
  
    public Date parseStringToDate(Object dateString) {  
        Date date = null;  
        if (dateString instanceof String)  
            try {                  
                    date = dateFormatWithSplash.parse((String) dateString);// 日期解析不行，就用日期时间解析  
     
            } catch (ParseException p) { 
               try{
            	   date = dateFormat.parse((String) dateString);// 只是日期解析  
            	}
            	catch (ParseException p1){
                throw new XWorkException(  
                        "两种yyyy-MM-dd与yyyy-MM-dd都解析不了，时间参数为"  
                                + dateString, p1);  
            	}
            }  
        return date;  
    }  
  
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(  
            "yyyy-MM-dd");
    private static final SimpleDateFormat dateFormatWithSplash=new SimpleDateFormat(  
            "MM/dd/yyyy");
  
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(  
            "yyyy-MM-dd HH:mm:ss");  
  
}  