package org.lecton.rest.rest.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class GenerateID {

	public static String getId(){
    	try{
		String id = UUID.randomUUID().toString();
    	if(id.length() >= 50){
        	id = id.substring(0, 49);
    	}
    	return  Base64.getEncoder().encodeToString(id.getBytes("utf-8"));
	  }catch(UnsupportedEncodingException e){
	         System.out.println("Error :" + e.getMessage());
	  }
    	return null;
	}	
	
	public static String getId(String userName ,String password){
    	try{
    		String id = userName + ":" + password;
    	return  Base64.getEncoder().encodeToString(id.getBytes("utf-8"));
	  }catch(UnsupportedEncodingException e){
	         System.out.println("Error :" + e.getMessage());
	  }
    	return null;
	}
}
