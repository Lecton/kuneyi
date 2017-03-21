package org.lecton.rest.rest.models.comments;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
    private String id;
    private String postId;
    private String description;
    private Timestamp timestamp;
    private String userId;
    private int commentLikes;
    
    public Comment(){
    	
    }

	public Comment(String id, String postId, String description, Timestamp timestamp, String userId, int commentLikes) {
		this.id = id;
		this.postId = postId;
		this.description = description;
		this.timestamp = timestamp;
		this.userId = userId;
		this.commentLikes = commentLikes;
	}
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public int getCommentLikes() {
		return commentLikes;
	}
	public void setCommentLikes(int commentLikes) {
		this.commentLikes = commentLikes;
	}
}
