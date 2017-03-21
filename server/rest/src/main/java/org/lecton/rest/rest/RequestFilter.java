package org.lecton.rest.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	private static final List<String> SECURED_URL_PREFIXES = new ArrayList<>();
	/*private static final List<String> SECURED_URL_PREFIXES = Arrays.asList("users/user", "users/all", "shops/register",
																			"shops/all", "posts/upload", "posts/notification", 
																			"posts/all");
	*/
	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
	
		
		
		requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		requestContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
	    requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
	    requestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	    requestContext.getHeaders().add("Access-Control-Max-Age", "1209600");
		
	    
    	//System.out.println("REQUEST FILTER IS ALIVE");
		String requestURI = requestContext.getUriInfo().getPath();
		
		if(urlContainsItemFromPrefixes(requestURI)){
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
			if(authHeader != null && authHeader.size() > 0){
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				
				//String decodeToken = Base64.decodeBase64(authToken);
				
				return;
			}
			
			System.out.println("WRONG:\t" + authHeader);
            
			Response unauthorizedStatus = Response
												.status(Response.Status.UNAUTHORIZED)
												.entity("User cannot access the resource.")
												.build();
			

			
		    
			requestContext.abortWith(unauthorizedStatus);
		}
	}
	
	public static boolean urlContainsItemFromPrefixes(String url)
	{
	    for(int i =0; i < SECURED_URL_PREFIXES.size(); i++)
	    {
	        if(url.contains(SECURED_URL_PREFIXES.get(i)))
	        {
	        	//System.out.println("URI:\t" + SECURED_URL_PREFIXES.get(i));
	            return true;
	        }
	    }
	    return false;
	}

}
