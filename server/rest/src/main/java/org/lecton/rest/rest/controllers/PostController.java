package org.lecton.rest.rest.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.lecton.rest.rest.models.UserNotification;
import org.lecton.rest.rest.models.comments.*;
import org.lecton.rest.rest.models.posts.LikePost;
import org.lecton.rest.rest.models.posts.NewPost;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.models.user.User;
import org.lecton.rest.rest.services.CommentService;
import org.lecton.rest.rest.services.PostService;
import org.lecton.rest.rest.services.UserService;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;


@Path("/posts")
public class PostController {

    private PostService postService = new PostService();
    private CommentService commentService = new CommentService();
    private UserService userService = new UserService();
    
    @Path("/upload")
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public UserNotification addPost(NewPost newPost){
    	
    	newPost = postService.writeToFile(newPost);
    	if(newPost != null){
        	postService.addPost(newPost);
    		return (new UserNotification("Post message:" ,"success"));
    	}else{
    		return (new UserNotification("Post message:" ,"not success"));
    	}
    }
     


    @Path("/like")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Post likePost(LikePost likePost){
    	
    	return postService.likePost(likePost.getId());
    }
    
    public final static String AUTH_KEY_FCM = "AAAArK7En0Y:APA91bGfav_0VomnQ2jCtXcC_sfN0d2_YNaV3GWj-Ko-xmN_AcY8SkHh4Yva7fILuAAVkBNGDODcuc9nL2L0wRTRa0WhcYhFP5PU0MM9OJ6Zy-xykLB3foTDdBUUz5NjaXcIJ69WFNUH";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    
    @Path("/notification")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON) 
    public String notification(UserNotification notification) throws Exception{
    
		String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		String FMCurl = API_URL_FCM; 
		
		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	
		   conn.setUseCaches(false);
		   conn.setDoInput(true);
		   conn.setDoOutput(true);

		   conn.setRequestMethod("POST");
		   conn.setRequestProperty("Authorization","key="+authKey);
		   conn.setRequestProperty("Content-Type","application/json");

		   List<User> users = userService.getAll();
		   
		   for(User user: users){

			   JSONObject json = new JSONObject();
			   json.put("to","euuYqlRJ1Yc:APA91bFd2iUHtNfRJvvtH-cclWoLIQV9Fo55ElZnl4tD0VmUJrtWMb8Oou6IJmQQfd3TKU94kVw1oNixMP0M8u7mfG-HzLMTsh_gTx5M5opLMyvVOqvuWorA0IMISwg95cJQYj-gjCyC");
			   JSONObject info = new JSONObject();
			   info.put("title", "Notificatoin Title"); // Notification title
			   info.put("body", "Hello Test notification"); // Notification body
			   json.put("notification", info);
	
			   OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			   wr.write(json.toString());
			   wr.flush();
			   System.out.println(json.toString());
			   InputStream in = conn.getInputStream();
			   
			   System.out.println(">>\t"+getStringFromInputStream(in));
			  
    		}
		  
    	return "";
    }
    
	// convert InputStream to String
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
    
    //2951712

	/*
	private boolean writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
													uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	*/

	/*
	private static final String FILE_PATH = "C://Users/Lecton/workspace/rest/images/axgpNyK_460s.jpg";
	
	@GET
	@Path("/get")
	@Produces("image/png")
	public Response getFile() {

		File file = new File(FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();

	}
	*/
	
    @Path("/post/{postId}") 
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getUser(@PathParam("postId") String postId){

    	Post post = postService.getPost(postId);
    	List<Comment> comments = commentService.getCommentsByPostId(postId);
    	
    	String html = "";
    	html += "<!DOCTYPE html>";
    	html += "<html>";
    	html += "<head>";
    	html += "<meta charset='UTF-8' content='width=device-width, initial-scale=1.0'>";
    	html += "<title>Title of the document</title>";

    	html += "<script src='http://localhost:8085/blob/jquery-2.1.3.min.js' ></script>";
    	html += "<script src='http://localhost:8085/blob/knockout-3.3.0.js' ></script>";
    	
    	html += "<script>";

    	html += "function test(){"
    			+ "console.log('alive man');"
    			+ "}";
    	html += "$(document).ready(function(){";

    	html += "console.log('Loaded');";	
    	
    	html += "$('#submit_comment').click(function(){";
    	html += "event.preventDefault();";
    	html += "console.log('I am Alive.');";	
    	html += "});";	
		
		html += "});";
    	html += "</script>";
    	
    	html += "</head>";
    	html += "<body>";
    	
    	html += "<table>";
    	html += "<tbody>";
    	html += "<tr>";
    	html += "<td>";
    	html += "<img src=" + post.getImageSource() + ">";
    	html += "</td>";
    	html += "</tr>";
    	html += "</tbody>";
    	html += "</table>";

		html += "<form>";
		html += "<textarea>";
		html += "</textarea>";
		html += "<input type='submit' value='Submit' id='submit_comment'>";
		html += "</form>";
		
    	System.out.print(comments.size());
    	if(comments.size() > 0){
    		
        	html += "<table>";
        	html += "<tbody>";
        	
        	for(Comment comment : comments){
        		
        		html += "<tr>";
        		html += "<td>";
        		html += "<label> " + comment.getDescription() + "</label>";
        		html += "</td>";
        		html += "</tr>";
				html += "<tr>";
				html += "<td>";
				html += "<button>Like</button>";
				html += "<label  />";
				html += "</td>";
				html += "</tr>";
        	}
        	html += "</tbody>";
        	html += "</table>";
        	
    	}
    	html += "</body>";
    	html += "</html>";
    	return html;
       //return postService.getPost(userId);
    }
    
	@Path("/all")
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAll() {
       return postService.getAll();
    }
	

	
}
