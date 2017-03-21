package org.lecton.rest.rest.controllers;


import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.lecton.rest.rest.models.UserNotification;
import org.lecton.rest.rest.models.user.NewUser;
import org.lecton.rest.rest.models.user.UserToken;
import org.lecton.rest.rest.models.user.UserWebToken;
import org.lecton.rest.rest.models.utils.SessionInfo;
import org.lecton.rest.rest.models.user.User;
import org.lecton.rest.rest.services.UserService;
import org.lecton.rest.rest.utils.GenerateDate;
import org.lecton.rest.rest.utils.GenerateID;

@Path("/users")
public class UserController {

    private UserService userService = new UserService();
    String homeReffLink = "http://192.168.43.198:8089/rest/webapi/users/user/";

    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) 
    public Response addUser(NewUser u)
    {
    	User user = new User();
    	user.setId(GenerateID.getId());
    	user.setName(u.getName());
    	user.setPassword(u.getPassword());
    	user.setUserSecurityToken(GenerateID.getId(u.getName() , u.getPassword()));
    	
    	
    	if(!userService.addUser(user)){
			 String result = "UserControl -> addUser : Could not add user into the database.";
			return Response.status(500).entity(result).build();
    	}
    	
    	try {
			return email(u.getName() , homeReffLink + user.getId());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String result = "UserControl -> addUser";
        return Response.status(201).entity(result).build();
        //String result = "Record entered: " + student;
        //return Response.status(201).entity(result).build();
    }
    

    @Path("/updateWebToken")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) 
    public UserNotification updateWebToken(UserWebToken u)
    {
    	 
    	return userService.updateWebToken(u);
        //String result = "Record entered: " + student;
        //return Response.status(201).entity(result).build();
    }
    
    public Response email(String email, String link) throws AddressException, MessagingException
    {
    	Properties mailServerProperties;
    	Session getMailSession;
    	MimeMessage generateMailMessage;

		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		//System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session.."); 
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
		generateMailMessage.setSubject("Email Verification");
		String emailBody = "<br>Greetings from Kuneyi</br><br></br>Please verify your email by clicking the following link: <a href=" + link + "> Email Verification. </a> " + "<br><br> Regards, <br>Kuneyi Admin";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "kuneyitourism@gmail.com", "administratorroot"); 
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
        String result = "Verification Sent.";
        return Response.status(201).entity(result).build();
    }
    
    @Path("/updateInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) 
    public UserNotification updateInfo(UserWebToken u)
    {
    	
    	return userService.updateWebToken(u);
        //String result = "Record entered: " + student;
        //return Response.status(201).entity(result).build();
    }
    
    
 
	@Path("/user/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
	public SessionInfo loggin(NewUser nUser){
		  User user = userService.getUser(nUser);
		  if(user == null){
		        //String result = "User does not exist.";
		        return null;
		  }
		  return (new SessionInfo(user.getId(),user.getUserSecurityToken()));
	}
	
	@Path("/user/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") String userId){
		
	   //TODO: Search for UID
	   //Flag valid
	   //if email valid, set flag to true : false
	  User user = userService.getUser(userId);
	  if(user == null){
	        //String result = "User does not exist.";
	        return null;
	  }
	  
	  user.setUserVerified(true);
	  user.setUserDateVerified(GenerateDate.getCurrentTimeStamp());
	  
	  if(userService.updateUserInfo(user)){
        String result = "Update successfull.";
        return user;
		  
	  }
	  
       return null;
    }
	
	
	@Path("/all")
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
       return userService.getAll();
    }
	
}
