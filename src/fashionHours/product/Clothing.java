package fashionHours.product;

import fashionHours.shop.Shop;

public class Clothing extends Product {

private SizeClothes size;
	
	public Clothing(Brand brand, ForGender gender, KindClothes kindClothing, String description, double price, SizeClothes size, Shop s) {
		super(brand, gender, TypeProduct.CLOTHES, description, price);
		this.size = size;
		shop=s;
	}


	
}
