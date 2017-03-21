package org.lecton.rest.rest.models.comments;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewComment {
    private String description;
    private String userId;
    private String postId;
    

	public NewComment() {
	}
	


	public NewComment(String description, String userId, String postId) {
		//super();
		this.description = description;
		this.userId = userId;
		this.postId = postId;
	}
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
}
