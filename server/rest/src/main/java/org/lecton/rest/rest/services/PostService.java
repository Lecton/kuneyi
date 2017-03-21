package org.lecton.rest.rest.services;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.lecton.rest.rest.models.posts.LikePost;
import org.lecton.rest.rest.models.posts.NewPost;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.models.user.User;
import org.lecton.rest.rest.utils.DBConnect;
import org.lecton.rest.rest.utils.GenerateID;

public class PostService {

    DBConnect dbConnect;
    private String imageUri = "C:/Users/Lecton/Desktop/shared_resource/images/";
    private String networkUri = "http://196.249.8.93:8085/images/";
    
    public PostService(){
		dbConnect = new DBConnect();
    }

    public void addPost(NewPost newPost){
    	try {
    		Post post = new Post();
    		post.setDescription(newPost.getDescription());
    		post.setId(GenerateID.getId());
    		post.setImageSource(newPost.getImageSource());
    		post.setPostLikes(0);
    		post.setTimestamp(new Timestamp(new Date().getTime()));
    		post.setUserId("9558034a-180b-4497-afb0-dac3e584f801");
    		post.setUserName("Thomas");
			dbConnect.addPost(post);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Post likePost(String postId){
    	try {
    		
    		Post post = getPost(postId);
    		int likesCount = post.getPostLikes() + 1;
			int rows = dbConnect.updatePostLike(postId, likesCount);
			
			if(rows == 1){
				return getPost(postId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public Post getPost(String userId){
    	try {
    		ResultSet rs = dbConnect.getPost(userId);

    		rs.next();
    		
		    String postId = rs.getString("postId");
		    String postDescription = rs.getString("postDesciption");
		    String postImage = rs.getString("postImage");
		    String fk_userId = rs.getString("fk_userId");
		    String userName = rs.getString("userName");
		    int postLikes = rs.getInt("postLikes");
		    Timestamp postTimeStamp = rs.getTimestamp("postTimeStamp");

			return (new Post(postId, postDescription, postImage, fk_userId, userName, postTimeStamp, postLikes));
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    //public LikePost likePost(LikePost likePost){
    	
    //}
    
    public List<Post> getAll() {

		List<Post> posts = new ArrayList<>();
        	try{
			
        		dbConnect = new DBConnect();
				ResultSet rs;
				rs = dbConnect.getAllPosts();
	
				while (rs.next())
				{

				    String postId = rs.getString("postId");
				    String postDescription = rs.getString("postDesciption");
				    String postImage = rs.getString("postImage");
				    String fk_userId = rs.getString("fk_userId");
				    String userName = rs.getString("userName");
				    int postLikes = rs.getInt("postLikes");
				    Timestamp postTimeStamp = rs.getTimestamp("postTimeStamp");
				    
					Post post = new Post(postId, postDescription, postImage, fk_userId, userName, postTimeStamp, postLikes);
					posts.add(post);
;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return posts;
    }

    public NewPost writeToFile(NewPost newPost){
    	try {
    		
	    	String imageName = GenerateID.getId();
	    	
	    	//post.setId(id);
	    	String imagePath = imageUri + imageName + ".png";
	    	//postService.addPost(post);
	    	
	
			BufferedImage image = null;
			byte[] imageByte;
			
			Base64 decoder = new Base64();
			imageByte = decoder.decode(newPost.getImageSource().getBytes());
			

			//imageByte = decoder.decodeBase64(post.getImageSource());
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			bis.close();
		
			image = ImageIO.read(bis);
			// write the image to a file
			File outputfile = new File(imagePath);
			ImageIO.write(image, "png", outputfile);
			
			
			//location of image
	    	newPost.setImageSource(networkUri + imageName + ".png");
	    	return newPost;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }    
}

