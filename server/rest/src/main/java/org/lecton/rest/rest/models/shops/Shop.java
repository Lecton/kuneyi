package org.lecton.rest.rest.models.shops;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Shop {
		
	    private String shopId;
	    private String shopName;
	    private String shopCategory;
	    private String shopArea;
	    private String shopAddress;
	    private String shopEmail;
	    private String shopTelephone;
	    private String shopFax;
	    private int shopRating;
	    private float shopLatitude;
	    private float shopLongitude;

		public Shop(){
	    }
		
	    public Shop(String shopId, String shopName, String shopCategory, String shopArea, String shopAddress,
				String shopEmail, String shopTelephone, String shopFax, int shopRating, float shopLatitude,
				float shopLongitude) {
			super();
			this.shopId = shopId;
			this.shopName = shopName;
			this.shopCategory = shopCategory;
			this.shopArea = shopArea;
			this.shopAddress = shopAddress;
			this.shopEmail = shopEmail;
			this.shopTelephone = shopTelephone;
			this.shopFax = shopFax;
			this.shopRating = shopRating;
			this.shopLatitude = shopLatitude;
			this.shopLongitude = shopLongitude;
		}

	    public String getShopId() {
			return shopId;
		}
	    
	    public String getShopName() {
			return shopName;
		}
	    
	    public void setShopId(String shopId) {
			this.shopId = shopId;
		}
	    
	    public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getShopCategory() {
			return shopCategory;
		}

		public void setShopCategory(String shopCategory) {
			this.shopCategory = shopCategory;
		}

		public String getShopEmail() {
			return shopEmail;
		}

		public void setShopEmail(String shopEmail) {
			this.shopEmail = shopEmail;
		}

		public String getShopAddress() {
			return shopAddress;
		}

		public void setShopAddress(String shopAddress) {
			this.shopAddress = shopAddress;
		}

		public String getShopArea() {
			return shopArea;
		}

		public void setShopArea(String shopArea) {
			this.shopArea = shopArea;
		}

		public String getShopTelephone() {
			return shopTelephone;
		}

		public void setShopTelephone(String shopTelephone) {
			this.shopTelephone = shopTelephone;
		}

		public String getShopFax() {
			return shopFax;
		}

		public void setShopFax(String shopFax) {
			this.shopFax = shopFax;
		}

		public int getShopRating() {
			return shopRating;
		}

		public void setShopRating(int shopRating) {
			this.shopRating = shopRating;
		}

		public float getShopLatitude() {
			return shopLatitude;
		}

		public void setShopLatitude(float shopLatitude) {
			this.shopLatitude = shopLatitude;
		}

		public float getShopLongitude() {
			return shopLongitude;
		}

		public void setShopLongitude(float shopLongitude) {
			this.shopLongitude = shopLongitude;
		}
	}