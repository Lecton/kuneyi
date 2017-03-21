package org.lecton.rest.rest.models.posts;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {
	
    private String id;
    private String description;
    private String imageSource;    
    private String userId;
    private Timestamp timestamp;
    private int postLikes;
    private String userName;

   public Post() {
   }
   
   public Post(String _id,String _description,String _imageSource,String _userId,String _userName, Timestamp _timestamp,int _postLikes) {
		id = _id;
		description = _description;
		imageSource = _imageSource;
		userId = _userId;
		userName = _userName;
		timestamp = _timestamp;
		postLikes = _postLikes;
   }

   public String getDescription() {
	   return description;
   }
   
   public String getId() {
	   return id;
   }
   
   public String getImageSource() {
	   return imageSource;
   }
   
   public String getUserId() {
		return userId;
	}
   
   public int getPostLikes() {
	   return postLikes;
   }
   
   public Timestamp getTimestamp() {
	   return timestamp;
   }
   
	public String getUserName() {
		return userName;
	}
	
   public void setDescription(String description) {
		this.description = description;
	}
   
   public void setId(String id) {
		this.id = id;
	}
   
   public void setImageSource(String imageSource) {
	this.imageSource = imageSource;
   }
   
   public void setUserId(String userId) {
		this.userId = userId;
   }
   
	public void setPostLikes(int postLikes) {
		this.postLikes = postLikes;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
