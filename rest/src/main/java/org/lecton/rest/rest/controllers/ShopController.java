package org.lecton.rest.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lecton.rest.rest.models.UserNotification;
import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.models.shops.NewShop;
import org.lecton.rest.rest.models.shops.Shop;
import org.lecton.rest.rest.models.user.NewUser;
import org.lecton.rest.rest.models.user.UserToken;
import org.lecton.rest.rest.models.user.User;
import org.lecton.rest.rest.services.PostService;
import org.lecton.rest.rest.services.ShopService;
import org.lecton.rest.rest.utils.GenerateID;

@Path("/shops")
public class ShopController {

    private ShopService shopService = new ShopService();
	
    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) 
    public UserNotification addShop(NewShop s)
    {
    	Shop shop = new Shop();
    	shop.setShopId(GenerateID.getId());
    	shop.setShopName(s.getShopName());
    	shop.setShopAddress(s.getShopAddress());
    	shop.setShopArea(s.getShopArea());
    	shop.setShopCategory(s.getShopCategory());
    	shop.setShopEmail(s.getShopEmail());
    	shop.setShopFax(s.getShopFax());
    	shop.setShopLatitude(s.getShopLatitude());
    	shop.setShopLongitude(s.getShopLongitude());
    	shop.setShopRating(s.getShopRating());
    	shop.setShopTelephone(s.getShopTelephone());
    	shopService.addShop(shop);
    	return (new UserNotification("Shop Message", "Shop has been succesfully added"));
        //String result = "Record entered: " + student;
        //return Response.status(201).entity(result).build();
    }    
    @Path("/all") 
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getAll(){
       return shopService.getAll();
    }
}
