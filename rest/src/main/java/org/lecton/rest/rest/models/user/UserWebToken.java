package org.lecton.rest.rest.models.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserWebToken {

    private String userId;
    private String userSecurityToken;
    private String userWebToken;
    
    public UserWebToken() {
		// TODO Auto-generated constructor stub
	}

	public UserWebToken(String userId, String userSecurityToken, String userWebToken) {
		super();
		this.userId = userId;
		this.userSecurityToken = userSecurityToken;
		this.userWebToken = userWebToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
