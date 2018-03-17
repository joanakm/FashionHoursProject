package fashionHours.product;

import java.util.Map;

import fashionHours.User;

public class Order {

	private static int count = 0;
	private int id=0;
	private User user;
	private Map<Integer,Product> products;
	private double totalCost;
	
	public Order(double cost, Map<Integer,Product> products) {
		this.id=count++;
		this.products=products;
	}
	
}
