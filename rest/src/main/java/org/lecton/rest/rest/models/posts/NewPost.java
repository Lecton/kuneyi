package org.lecton.rest.rest.models.posts;


public class NewPost {

    private String description;
    private String imageSource;
    

    public NewPost() {
    }
    
    public NewPost(String _description,String _imageSource){
 		description = _description;
 		imageSource = _imageSource;
 	}
    
    public String getDescription() {
		return description;
	}
    
    public String getImageSource() {
		return imageSource;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}
    
    public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}
}
