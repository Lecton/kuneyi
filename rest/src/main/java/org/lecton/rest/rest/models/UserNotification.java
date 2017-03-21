package org.lecton.rest.rest.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserNotification {
	String title;
	String message;
	
    public UserNotification(){
    }

    public UserNotification(String t ,String m){
    	title = t;
    	message = m;
    }
    
    public String getMessage() {
		return message;
	}
    
    public String getTitle() {
		return title;
	}
    
    public void setMessage(String message) {
		this.message = message;
	}
    
    public void setTitle(String title) {
		this.title = title;
	}
}
