package com.createidea.scrumfriend.utils;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class IntegerConvertor extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		// TODO Auto-generated method stub
		if (Integer.class == toClass) {  
            String doubleStr = values[0];  
            Integer d=0;
			try {
				d = Integer.parseInt(doubleStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return d; 				 
			}  
            return d;  
        }
		return 0;   

	}

	@Override
	public String convertToString(Map context, Object o) {
		// TODO Auto-generated method stub
		   return o.toString();  

	}

}
