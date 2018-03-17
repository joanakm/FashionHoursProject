package fashionHours.product;

import fashionHours.shop.Shop;

public class Shoes extends Product {

private int size; 
	
	public Shoes(Brand brand, ForGender gender, KindShoes kindShoes, String description, double price, int size, Shop s) {
		super(brand, gender, TypeProduct.SHOES, description, price);
		this.size = size;
		shop=s;
	}
	
}
