package fashionHours.product;

import java.util.HashSet;
import java.util.Set;

import fashionHours.shop.Shop;

public abstract class Product{

	public enum ForGender{
		WOMAN,MAN
	}
	
	public enum TypeProduct{
		CLOTHES,SHOES,ACCSSESORIES
	}
	
	public enum KindClothes{
		BLOUSES,JEANS,JACKETS,TROUSERS,SKIRTS,SHIRTS,TSHIRTS,SWIMSUITS, HOODEDSWEATSHIRTS,DRESSES,BRASSIERES,SOCKS,COATS
	}
	
	public enum KindShoes{
		CLASSIC, BOOTS, SHOES, SANDALS, SPORT
	}
	
	public enum KindAccessories{
		JEWELRY, BELTS, WALLETS, KEYCHAINS, GLASSES, SUNGLASSES, WATCHES, SHAWLS, HATS, UMBRELLAS, BACKPACKS, BAGS
	}
	
	public enum Brand{
		NEXT,ESPRIT,ZEE,LANE,PEPE,JEANS,LONDON,PUMA,GUESS,DIESEL,DESIGUAL,CALVIN,KLEIN,
		COLLECTION,ASICS,CONVERSE,TED,BAKER,FOSSIL,DENIM,LIU,JO,NIKE,ADIDAS,ORIGINALS,LE,
		COQ,SPORTIF,LOVE,MOSCHINO
	}
	
	public enum SizeClothes{
		XS,S,M,L,XL
	}
	
	private static int count = 0;
	private Brand brand;
	private TypeProduct typeProduct;
	private String description;
	private int quantity;
	private int id=0;
	private double price;
	protected static Shop shop;
	
	public Product(Brand brand, ForGender gender, TypeProduct product, String description, double price) {
	
		this.brand = brand;
		this.typeProduct = product;
		this.description = description;
		this.price = price;
		this.id = count++;
	}
	
	public Set<Product> search(String s){
		Set<Product> searchedProducts =new HashSet<Product>();
		for(Product p : shop.getProductsOnly() ) {
			if(p.getDescription().contains(s)) {
				searchedProducts.add(p);
			}
		}
		return searchedProducts;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ID:"+id+", DESCRIPTION:"+description+", QUANTITY: "+quantity+", PRICE:"+price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void increaseQuantity() {
		this.setQuantity(this.getQuantity()+1);
	}
	
	public String getDescription() {
		return description;
	}
	
	
}
