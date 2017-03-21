package org.lecton.rest.rest.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lecton.rest.rest.models.posts.Post;
import org.lecton.rest.rest.models.shops.Shop;
import org.lecton.rest.rest.utils.DBConnect;

public class ShopService {
    DBConnect dbConnect;
    
    public ShopService(){
		dbConnect = new DBConnect();
    }

    public void addShop(Shop shop){
    	try {
			dbConnect.addShop(shop);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<Shop> getAll() {

		List<Shop> shops = new ArrayList<>();
        	try{
			
        		dbConnect = new DBConnect();
				ResultSet rs;
				rs = dbConnect.getAllShops();
	
				while (rs.next())
				{

				    String shopId = rs.getString("shopId");
				    String shopName = rs.getString("shopName");
				    String shopCategory = rs.getString("shopCategory");
				    String shopArea = rs.getString("shopArea");
				    String shopAddress = rs.getString("shopAddress");
				    String shopEmail = rs.getString("shopEmail");
				    String shopTelephone = rs.getString("shopTelephone");
				    String shopFax = rs.getString("shopFax");
				    int shopRating = rs.getInt("shopRating");
				    float shopLatitude = rs.getFloat("shopLatitude");
				    float shopLongitude = rs.getFloat("shopLongitude");
				    
				    


					Shop shop = new Shop(shopId, shopName, shopCategory, shopArea, shopAddress, shopEmail, shopTelephone, shopFax, shopRating, shopLatitude, shopLongitude);
					shops.add(shop);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return shops;
    }
}
