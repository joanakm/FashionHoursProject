package fashionHours.product;

import fashionHours.shop.Shop;

public class Accessories extends Product {

	public Accessories(Brand brand, ForGender gender, KindAccessories kindAccessories, String description, double price, Shop s) {
		super(brand, gender, TypeProduct.ACCSSESORIES, description, price);
		shop=s;
	}
	
}
