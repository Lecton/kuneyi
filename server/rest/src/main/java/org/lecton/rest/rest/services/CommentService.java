package org.lecton.rest.rest.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lecton.rest.rest.models.comments.Comment;
import org.lecton.rest.rest.models.comments.NewComment;
import org.lecton.rest.rest.models.posts.NewPost;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.utils.DBConnect;
import org.lecton.rest.rest.utils.GenerateID;

public class CommentService {

    DBConnect dbConnect;
    
	public CommentService(){
		dbConnect = new DBConnect();
	}
	
    public void addComment(NewComment newComment){
    	try {
    		Comment comment = new Comment();
    		comment.setDescription(newComment.getDescription());
    		comment.setUserId(newComment.getUserId());
    		comment.setId(GenerateID.getId());
    		comment.setPostId(newComment.getPostId());
    		comment.setCommentLikes(0);
    		comment.setTimestamp(new Timestamp(new Date().getTime()));
			dbConnect.addComment(comment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<Comment> getCommentsByPostId(String pId) {

		List<Comment> comments = new ArrayList<>();
        	try{
			
        		dbConnect = new DBConnect();
				ResultSet rs;
				rs = dbConnect.getCommentsByPostId(pId);
	
				while (rs.next())
				{
					String id = rs.getString("commentId");
				    String postId = rs.getString("fk_postId");
				    String userId = rs.getString("fk_userId");
				    String description = rs.getString("commentDesciption");
				    Timestamp timestamp = rs.getTimestamp("commentTimeStamp");
				    int commentLikes = rs.getInt("commentLikes");
					Comment post = new Comment(id, postId, description, timestamp, userId, commentLikes);
							
					comments.add(post);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return comments;
    }
    
    public List<Comment> getAll() {

		List<Comment> comments = new ArrayList<>();
        	try{
			
        		dbConnect = new DBConnect();
				ResultSet rs;
				rs = dbConnect.getAllComments();
	
				while (rs.next())
				{
					String id = rs.getString("commentId");
				    String postId = rs.getString("fk_postId");
				    String userId = rs.getString("fk_userId");
				    String description = rs.getString("commentDesciption");
				    Timestamp timestamp = rs.getTimestamp("commentTimeStamp");
				    int commentLikes = rs.getInt("commentLikes");
					Comment post = new Comment(id, postId, description, timestamp, userId, commentLikes);
							
					comments.add(post);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return comments;
    }    
}
