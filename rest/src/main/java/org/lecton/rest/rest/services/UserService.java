package org.lecton.rest.rest.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lecton.rest.rest.models.user.UserToken;
import org.lecton.rest.rest.models.user.UserWebToken;
import org.lecton.rest.rest.models.UserNotification;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.models.user.NewUser;
import org.lecton.rest.rest.models.user.User;
import org.lecton.rest.rest.utils.DBConnect;

public class UserService {

    DBConnect dbConnect;

    public UserService(){
		dbConnect = new DBConnect();
    }

    public boolean addUser(User user){
    	
    	try {
	    	if(dbConnect.addUser(user)){
	    		User u = new User(user.getId() , user.getUserSecurityToken());
	    		return true;
	    	}
	    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

    public User getUser(String uId) {

    	try {
    		

    		ResultSet rs = dbConnect.getUser(uId);

    		rs.next();
    		
			String id = rs.getString("userId");
			String name = rs.getString("userName");
			String password = rs.getString("userPassword");
			String userSecurityToken = rs.getString("userSecurityToken");
			String userWebToken = rs.getString("userWebToken");
			boolean userVerified = rs.getBoolean("userVerified");
			String userDateVerified = rs.getString("userDateVerified");
			
			User user = new User(id, name, password, userSecurityToken, userWebToken, userVerified, userDateVerified);
	    	return user;
	    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public User getUser(NewUser newUser) {

    	try {
    		

    		ResultSet rs = dbConnect.getUser(newUser);

    		rs.next();
    		
			String id = rs.getString("userId");
			String name = rs.getString("userName");
			String password = rs.getString("userPassword");
			String userSecurityToken = rs.getString("userSecurityToken");
			String userWebToken = rs.getString("userWebToken");
			boolean userVerified = rs.getBoolean("userVerified");
			String userDateVerified = rs.getString("userDateVerified");
			
			User user = new User(id, name, password, userSecurityToken, userWebToken, userVerified, userDateVerified);
	    	return user;
	    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

    
    public boolean updateUserInfo(User user){
    	try {
    		
			int rows = dbConnect.updateUserInfo(user);
			
			if(rows == 1){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

    public UserNotification updateWebToken(UserWebToken u){
    	
	    	if(dbConnect.updateUserWebToken(u)){
	    		return (new UserNotification("Web Token" , "Web Token Update Succesful"));
	    	}
	    	
    		return null;
    }
    
    public List<User> getAll() {

		List<User> users = new ArrayList<>();
        	try{
			
        		dbConnect = new DBConnect();
				ResultSet rs;
				rs = dbConnect.getAllUsers();
	
				while (rs.next())
				{

				    String userId = rs.getString("userId");
				    String userName = rs.getString("userName");
				    String userWebToken = rs.getString("userWebToken");

					User user = new User(userId ,userName, userWebToken);
					users.add(user);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return users;
    } 
}
