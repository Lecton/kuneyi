package org.lecton.rest.rest.models.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewUser  {
	
    private String name;
    private String password;

    

	public NewUser(){
    }
	
	

	public NewUser(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}