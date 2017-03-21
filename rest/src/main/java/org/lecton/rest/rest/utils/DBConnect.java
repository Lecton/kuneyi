package org.lecton.rest.rest.utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lecton.rest.rest.models.comments.Comment;
import org.lecton.rest.rest.models.posts.LikePost;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.models.shops.Shop;
import org.lecton.rest.rest.models.user.NewUser;
import org.lecton.rest.rest.models.user.User;
import org.lecton.rest.rest.models.user.UserToken;
import org.lecton.rest.rest.models.user.UserWebToken;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBConnect {
	
    String myDriver = "org.gjt.mm.mysql.Driver";
    String url = "jdbc:mysql://localhost:3306/id700265_cloud_messaging?autoReconnect=true&useSSL=false";
    Connection connnection = null;
    String user = "root";
    String password = "";

    public DBConnect(){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            connnection = DriverManager.getConnection(url, user, password);
        }catch (Exception ex){
            System.out.println("Erro:\t" + ex);
        }

    }

    public ResultSet getAllPosts() throws SQLException {

        String query = "SELECT * FROM POSTS";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }

    public int updatePostLike(String postId ,int likes) throws SQLException{

    	String query = "UPDATE POSTS SET postLikes = " + likes + " WHERE postId = '" + postId + "'";

        java.sql.Statement st = connnection.createStatement();
        
        return st.executeUpdate(query);
    }
    
    public int updateUserInfo(User user) throws SQLException{
    	String query = "UPDATE USERS SET userName = '" + user.getName() + "' ,userPassword = '" + user.getPassword() + "' ,userSecurityToken = '" + user.getUserSecurityToken() + 
    			"' ,userWebToken = '" + user.getUserWebToken()+ "' ,userVerified = " + user.isUserVerified() + " ,userDateVerified = '" + user.getUserDateVerified() + "' WHERE userId = '" + user.getId() + "'";

        java.sql.Statement st = connnection.createStatement();
        
        return st.executeUpdate(query); 
    	
    }
    
    public ResultSet getPost(String postId) throws SQLException {

        String query = "SELECT * FROM POSTS WHERE postId = '" + postId + "'";

        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query); 
        
        return rs;
    }

    public ResultSet getAllComments() throws SQLException {

        String query = "SELECT * FROM COMMENTS";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public ResultSet getCommentsByPostId(String postId) throws SQLException {
        String query = "SELECT * FROM COMMENTS WHERE fk_postId = '" + postId + "'";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public ResultSet getUser(String userId) throws SQLException {

        String query = "SELECT * FROM USERS WHERE userId = '" + userId + "'";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public ResultSet getUser(NewUser newUser) throws SQLException {

        String query = "SELECT * FROM USERS WHERE userName = '" + newUser.getName() + "' AND userPassword = '" + newUser.getPassword() + "'";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
        
    public ResultSet getAllUsers() throws SQLException {

        String query = "SELECT * FROM USERS";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public ResultSet getAllShops() throws SQLException {

        String query = "SELECT * FROM SHOPS";
        java.sql.Statement st = connnection.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public ResultSet addPost(Post post) throws SQLException{

    	try{
		        String query = "INSERT INTO posts (postId, postDesciption, postImage, fk_userId, userName, postLikes, postTimeStamp) VALUES (? ,? ,? ,? ,? ,? ,?)";
		
		        PreparedStatement preparedStmt = (PreparedStatement) connnection.prepareStatement(query);
		        preparedStmt.setString(1, post.getId());
		        preparedStmt.setString(2, post.getDescription());
		        preparedStmt.setString(3, post.getImageSource());
		        preparedStmt.setString(4, post.getUserId());
		        preparedStmt.setString(5, post.getUserName());
		        preparedStmt.setInt(6, post.getPostLikes());
		        preparedStmt.setTimestamp(7, post.getTimestamp());
		        
		        preparedStmt.execute();
    	   }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    	return null;
    }

    public boolean addComment(Comment comment) throws SQLException{

    	try{
		        String query = "INSERT INTO COMMENTS (commentId, fk_postId ,fk_userId, commentDesciption, commentTimeStamp, commentLikes) VALUES (? ,? ,? ,? ,? ,?)";
		
		        PreparedStatement preparedStmt = (PreparedStatement) connnection.prepareStatement(query);
		        preparedStmt.setString(1, comment.getId());
		        preparedStmt.setString(2, comment.getPostId());
		        preparedStmt.setString(3, comment.getUserId());
		        preparedStmt.setString(4, comment.getDescription());
		        preparedStmt.setTimestamp(5, comment.getTimestamp());
		        preparedStmt.setInt(6, comment.getCommentLikes());
		        
		        preparedStmt.execute();
		        
		        return true;
    	   }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    	return false;
    }
    
    public boolean addUser(User user) throws SQLException{

    	try{
		        String query = "INSERT INTO USERS (userId, userName, userPassword, userSecurityToken, userWebToken) VALUES (? ,? ,? ,?,?)";
		
		        PreparedStatement preparedStmt = (PreparedStatement) connnection.prepareStatement(query);
		        preparedStmt.setString(1, user.getId());
		        preparedStmt.setString(2, user.getName());
		        preparedStmt.setString(3, user.getPassword());
		        preparedStmt.setString(4, user.getUserSecurityToken());
		        preparedStmt.setString(5, user.getUserWebToken());
		        
		        preparedStmt.execute();
		        
		        return true;
    	   }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    	return false;
    }
    
    public boolean addShop(Shop shop) throws SQLException{

    	try{
		        String query = "INSERT INTO shops(shopId, shopName, shopCategory, shopArea, shopAddress, shopEmail, shopTelephone, shopFax, shopRating, shopLatitude, shopLongitude) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		        PreparedStatement preparedStmt = (PreparedStatement) connnection.prepareStatement(query);
		        preparedStmt.setString(1, shop.getShopId());
		        preparedStmt.setString(2, shop.getShopName());
		        preparedStmt.setString(3, shop.getShopCategory());
		        preparedStmt.setString(4, shop.getShopArea());
		        preparedStmt.setString(5, shop.getShopAddress());
		        preparedStmt.setString(6, shop.getShopEmail());
		        preparedStmt.setString(7, shop.getShopTelephone());
		        preparedStmt.setString(8, shop.getShopFax());
		        preparedStmt.setInt(9, shop.getShopRating());
		        preparedStmt.setFloat(10, shop.getShopLatitude());
		        preparedStmt.setFloat(11, shop.getShopLongitude());
		        
		        preparedStmt.execute();
		        
		        return true;
    	   }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    	return false;
    }
    
    public boolean updateUserWebToken(UserWebToken userWebToken){
    	try{
    		String query = "UPDATE users SET userWebToken = ? WHERE userId = ?";
	
	        PreparedStatement preparedStmt = (PreparedStatement) connnection.prepareStatement(query);
	        preparedStmt.setString(1, userWebToken.getUserWebToken());
	        preparedStmt.setString(2, userWebToken.getUserId());
	        
	        preparedStmt.execute();
	        
	        return true;
	   }
		catch (Exception e)
		{
		  System.err.println("Got an exception!");
		  System.err.println(e.getMessage());
		}
		return false;
    	
    }
}
