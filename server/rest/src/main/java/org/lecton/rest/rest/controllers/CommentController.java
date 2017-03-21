package org.lecton.rest.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lecton.rest.rest.models.UserNotification;
import org.lecton.rest.rest.models.comments.Comment;
import org.lecton.rest.rest.models.comments.NewComment;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.services.CommentService;
import org.lecton.rest.rest.services.PostService;

@Path("/comments")
public class CommentController {
	

    private CommentService commentService = new CommentService();
    
	@Path("/comment")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserNotification addComment(NewComment newComment){
		
		commentService.addComment(newComment);
    	return (new UserNotification("Comment message:" ,"success"));
    	//return comment;
    }
	

	@Path("/all")
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAll() {
       return commentService.getAll();
    }
	

    @Path("/{postId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentsByPostId(@PathParam("postId") String postId){
        return commentService.getCommentsByPostId(postId);
    }
}
