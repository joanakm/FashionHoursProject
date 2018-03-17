package fashionHours;

import java.io.IOException;
import java.util.Map;
import fashionHours.shop.*; 
import fashionHours.product.Product.*; 
import fashionHours.product.*;
import fashionHours.shop.Shop;


public class Demo {

	public static void main(String[] args) {
		
		Shop shop = Shop.createShop();
		User u=new User(shop);
		u.register();
	    System.out.println(u);
	    System.out.println();
		Product blouse = new Clothing(Brand.DENIM, ForGender.WOMAN, KindClothes.BLOUSES, "Pinky blouse", 14.99, SizeClothes.S, shop);
		
		Product sneakers = new Shoes(Brand.CONVERSE, ForGender.MAN, KindShoes.SPORT, "Classic sport shoes", 85, 43, shop); 

		Product bag = new Accessories(Brand.NEXT, ForGender.WOMAN, KindAccessories.BAGS, "Beach bag", 30, shop); 
		shop.addProduct(bag,5);  
		shop.addProduct(sneakers,4); 
        shop.addProduct(blouse,1); 
        shop.removeProduct(bag,3); 
		System.out.println("======= Products in the shop ======="); 
        System.out.println(shop); 
        
        Cart cart = new Cart(shop); 
		cart.add(bag, 2);  
		cart.add(sneakers, 2); 
		cart.add(blouse,1);    
		System.out.println("======= CART ======="); 
		System.out.println(cart); 
	    System.out.println("======= sorted by lowest price ======="); 
	    shop.sortbyLowestPrice(); 
	    System.out.println("======= sorted by highest price ======="); 
		shop.sortbyHighestPrice(); 
	    System.out.println("======= total cost of the cart ======="); 
		cart.totalCost(); 
	

	}	
	
}
