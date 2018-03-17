package fashionHours.product;

public class Clothing extends Product {

private SizeClothes size;
	
	public Clothing(Brand brand, ForGender gender, KindClothes kindClothing, String description, double price, SizeClothes size) {
		super(brand, gender, TypeProduct.CLOTHES, description, price);
		this.size = size;
	}


	
}
