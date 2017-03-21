package org.lecton.rest.rest.models.posts;

public class LikePost {

    private String id;

    public LikePost() {
    }
    
    public LikePost(String _id ,int _likes){
 		id = _id;
 	}
    
    public String getId() {
		return id;
	}
    
    public void setId(String id) {
		this.id = id;
	}
    
}