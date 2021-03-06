package org.lecton.rest.rest.models.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserToken {
    private String userId;
    private String userSecurityToken;

    public UserToken(){
    }

	public UserToken(String userId, String userSecurityToken) {
		super();
		this.userId = userId;
		this.userSecurityToken = userSecurityToken;
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
  
}
