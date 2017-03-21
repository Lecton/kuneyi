package org.lecton.rest.rest.models.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private String id;
    private String name;
    private String password;
    private String userSecurityToken;
    private String userWebToken;
    private boolean userVerified;
    private String userDateVerified;

    public User(){
    }


	public User(String id, String userSecurityToken){
		super();
		this.id = id;
		this.userSecurityToken = userSecurityToken;
	}
	
	public User(String id, String userSecurityToken,  String userWebToken){
		super();
		this.id = id;
		this.userSecurityToken = userSecurityToken;
		this.userWebToken = userWebToken;
	}
	
	
	public User(String id, String name, String password, String userSecurityToken, String userWebToken , boolean userVerified, String userDateVerified) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.userSecurityToken = userSecurityToken;
		this.userWebToken = userWebToken;
		this.userVerified = userVerified;
		this.userDateVerified = userDateVerified;
	}


	public boolean isUserVerified() {
		return userVerified;
	}


	public void setUserVerified(boolean userVerified) {
		this.userVerified = userVerified;
	}


	public String getUserDateVerified() {
		return userDateVerified;
	}


	public void setUserDateVerified(String userDateVerified) {
		this.userDateVerified = userDateVerified;
	}


	public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


	public String getUserSecurityToken() {
		return userSecurityToken;
	}


	public void setUserSecurityToken(String userSecurityToken) {
		this.userSecurityToken = userSecurityToken;
	}


	public String getUserWebToken() {
		return userWebToken;
	}

 
	public void setUserWebToken(String userWebToken) {
		this.userWebToken = userWebToken;
	}

}

