package fashionHours.shop;

import java.util.HashMap;

import fashionHours.User;
import fashionHours.product.Product;
import fashionHours.shop.Shop;

public class Cart {

	private HashMap<Integer,Product> productsInCart;
	private Shop shop;
	private double totalCost;

	public Cart(Shop shop) {
		this.shop = shop;
		this.productsInCart = new HashMap<>();
	}
	
	public void add(Product product, int quantity) {
		if(!(this.shop.getProductsInShop().containsKey(product.getId()))) {
			System.out.println("Sorry, this product is not available in the shop, you can not add it to your cart.");
			return;
		}
		else {
			product.setQuantity(quantity);
			this.productsInCart.put(product.getId(), product);
		}
		
	}
	
	public void emptyCart() {
		this.productsInCart.clear();
		this.totalCost=0;
	}
	
	public HashMap<Integer, Product> getProductsInCart() {
		return productsInCart;
	}
	
	public void remove(Product product, int quantity) {
		if(!(this.productsInCart.containsKey(product.getId()))) {
			System.out.println("Sorry, this product is not in the cart, you can not remove it.");
		}
		else {
			if(product.getQuantity() < quantity) {
				System.out.println("Sorry, the cart doesn't have "+quantity+" of this product.");
				return;
			}
			else {
				if(product.getQuantity() == quantity) {
					this.productsInCart.remove(product.getId());
				}
				else {
					this.productsInCart.get(product.getId()).setQuantity(product.getQuantity()-quantity);
				}
			}
		}
	}

	@Override
	public String toString() {
		
		String s = "";
		for(Product p : productsInCart.values()) {
			s += p.toString()+", TOTAL PRICE:"+p.getQuantity()*p.getPrice()+"\n";
		}
		return s;
	}

	public void totalCost() {
		double totalCost = 0;
		for(Product p : productsInCart.values()) {
			totalCost += p.getPrice()*p.getQuantity();
		}
		this.totalCost=totalCost;
		System.out.print("Cost:"+totalCost);
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
}
