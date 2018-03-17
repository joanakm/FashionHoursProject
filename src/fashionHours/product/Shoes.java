package fashionHours.product;

public class Shoes extends Product {

private int size; 
	
	public Shoes(Brand brand, ForGender gender, KindShoes kindShoes, String description, double price, int size) {
		super(brand, gender, TypeProduct.SHOES, description, price);
		this.size = size;
	}
	
}
