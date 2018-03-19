package fashionHours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import fashionHours.product.*;
import fashionHours.shop.Cart;
import fashionHours.shop.Shop;
public class User {

	private static final int MAX_CHARS_IN_PASS=8;
	private static int methodInvokeCounter=1;
	private String firstName;
	private String lastName;
	private String email;
	private Gender gender;
	private String password;
	private String phone;
	private ArrayList<Order> orders;
	private Map<String,String> addresses;
	private boolean isLoggedIn=false;
	private static Shop shop;
	private Cart cart;
	private boolean isAdmin=false;
	
	private enum Gender{
		MALE, FEMALE
	}
	
    public User(Shop s) {
		shop=s;
		this.addresses = new HashMap<String, String>();
		cart=new Cart(s);
	}
	
	public void login() {
		Scanner sc=new Scanner(System.in);
		System.out.println("EMAIL: ");
		String email=null;
		if(validateEmailAddress(email)) {
			System.out.println("PASSWORD: ");
			String pass=sc.nextLine();
			if(validatePassword(pass)) {
				if(this.email.equals(email) && this.password.equals(pass)) {
					isLoggedIn=true;
					if(shop.getAdmins().contains(this.email)) {
						isAdmin=true;
					}
				}else {
					System.out.println("Please, try again.");
				}
			}
		}
		sc.close();
	}
	
	public void logout() {
		isLoggedIn=false; 
	}
	
	//discount method, passing one product
	public void putUpForSale(Product productForSale, double discount) {
		//discount is measured in %
		if(isAdmin) {
			//set new price for the product
			productForSale.setPrice(productForSale.getDiscounterPrice(productForSale, discount));
		} else {
			System.out.println("Operation not allowed");
		} 
	}
	
	//discount method, passing set of products
	public void putUpForSale(Set<Product> productsForSale, double discount) {
		if(isAdmin) {
			for(Product p : productsForSale) {
				p.setPrice(p.getDiscounterPrice(p, discount));
			}
		}else {
			System.out.println("Operation not allowed");
		}
	}
	
	public void showOrders() {
		for(Order o: orders) {
			System.out.println(o);
		}
	}
	
	private boolean validatePhone(String ph) {
		return ph.matches("[0-9]+") && ph.length()==9;	
	}
	
	private boolean validateName(String name) {
		//matches any kind of letter from any language
	    return name.matches("^[\\p{L} .'-]+$");	
	}
	
	private boolean validatePassword(String pass) {
		//at least one digit
		//at least one upper case letter and one lower case letter
		//special character must occur at least once (?=.*[@#$%^&+=])
		//no whitespaces allowed
		//at least 8 chars
	    return pass.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}");	
	}
	
	private boolean validateEmailAddress(String e) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(e);
        return m.matches();
	}
	
 	public boolean enterPassword() {
		Scanner sc=new Scanner(System.in);
		String pass=null;
		do {
			System.out.println("Please, enter your password: ");
			pass=sc.nextLine();
			
			if(pass!=null && !pass.isEmpty()) {
				//check if it has at least 8 chars
				if(pass.length()<MAX_CHARS_IN_PASS) {
					System.out.println("Your password must be at least 8 symbols long.");
					continue;
				}
				//end check for number of chars
				//check for digit
				if(!checkForDigit(pass)) {
					System.out.println("Your password must contain at least one digit");
	                continue;
				}
                //end of check for digit
				//check for lower case letter
				if(!checkForLowerCase(pass)) {
					System.out.println("Your password must contain at least one lower case letter.");
					continue;
				}
				//end check for lower case letter
				//check for upper case letter
				if(!checkForUpperCase(pass)) {
					System.out.println("Your password must contain at least one upper case letter.");
					continue;
				}
				//end check for upper case letter
				//check if it contains spaces
				if(pass.contains(" ")) {
					System.out.println("Your password can not contain spaces.");
					continue;
				}
				//end check for spaces
				
				if(validatePassword(pass)) {
					this.password=pass;
					return true;
				}
			}
			
		}while(!validatePassword(pass)); 
		sc.close();
		return false;
	}
	
	private boolean checkForUpperCase(String p) {
		boolean upperCase = !p.equals(p.toLowerCase());
		if(upperCase) {
			return true;
		}
		return false;
	}
	
	private boolean checkForLowerCase(String p) {
		boolean lowerCase = !p.equals(p.toUpperCase());
		if(lowerCase) {
			return true;
		}
		return false;
	}
	
	private boolean checkForDigit(String p) {
		boolean hasDigit=false;
		for (char c : p.toCharArray()) {
             hasDigit= Character.isDigit(c);
            if(hasDigit) {
            	break;
            }
        }
		return hasDigit;
	}
	
	private boolean enterEmailAddress() {
        Scanner sc=new Scanner(System.in);
        String mail=null;
        do {
	        System.out.println("Please, enter your email address: ");
	        mail=sc.nextLine();
	        
	        if(validateEmailAddress(mail)) {
	        	if(shop.canRegister(mail)) {
	        		this.email=mail;
	        		shop.addEmailToSet(this.email);
		        	return true;
	        	}else {
	        		enterEmailAddress();
	        	}
	        }
	    }while(!validateEmailAddress(mail));
        sc.close();
        return false;
   }
	
	private boolean enterPhone() {
		Scanner sc=new Scanner(System.in);
		String phone=null;
		do {
			System.out.println("Please, enter your mobile phone: ");
			System.out.print("+359");
			phone=sc.nextLine();
			
			if(validatePhone(phone)) {
				this.phone=phone;
				return true;
			}
		}while(!validatePhone(phone));
		sc.close();
		return false;
	}
	
	private boolean enterName() {
		Scanner sc=new Scanner(System.in);
        String name=null;
	        do {
	        	
	        	if(methodInvokeCounter==1) {
				  System.out.println("Please, enter your first name: ");
	        	}
	        	if(methodInvokeCounter==2) {
	        	  System.out.println("Please, enter your last name: ");
	        	}
				name=sc.nextLine();
				 
				if(validateName(name)) {
					if(methodInvokeCounter==1) {
						this.firstName=name;
						methodInvokeCounter=2;
						return true;
					}
					if(methodInvokeCounter==2) {
						this.lastName=name;
						methodInvokeCounter=1;
						return true;
					}
					
				}
				 
	        }while(!validateName(name));
	       sc.close();
        return false;
	}
	
	private void enterAndValidateGender() {
		Scanner sc=new Scanner(System.in);
		String gender=null;
		
		while(true) {
			boolean valid=false;
			System.out.println("Please, enter your gender: ");
			gender=sc.nextLine().toLowerCase().trim();
			switch (gender){
			case "female": {
				this.gender=Gender.FEMALE;
				valid=true;
				break;
			}
			case "f":{
				this.gender=Gender.FEMALE;
				valid=true;
				break;
			}
			case "male": {
				this.gender=Gender.MALE;
				valid=true;
				break;
			}
			case "m":{
				this.gender=Gender.MALE;
				valid=true;
				break;
			}
			
			}
			if(valid) {
				break;
			}
		}
		
	}
	
	public void addAddress() {
		Scanner sc=new Scanner(System.in);
		String city=null;
		do {
		   System.out.println("Please, enter your city: ");
		   city=sc.nextLine().toUpperCase();
		}while(!shop.getCities().contains(city));
		System.out.println("Please, enter your street and number: ");
		String street=sc.nextLine();
		this.addresses.put(city, street);
		sc.close();
	}
	
	public void register() {
		//enter and validate first and last names
		enterName();
		enterName();
		
		//enter and validate gender
	    enterAndValidateGender();
		
		//enter and validate password
		enterPassword();
						
		//enter and validate email
		enterEmailAddress();
		
		//enter and validate phone
	    enterPhone();
		
		//add address
		addAddress();
		
		System.out.println("Welcome to FashionHours!");
	}
	
	public void changeName() {
		if(isLoggedIn) {
			enterName();
		    if(enterName()) {
		    	System.out.println("Your name has been changed.");
		    }
		}
	}
	
	public void placeOrder() {
		this.cart.emptyCart();
		this.orders.add(new Order(cart.getTotalCost(),cart.getProductsInCart()));
	}
	
	public void changePassword() {
		if(isLoggedIn) {
			Scanner sc=new Scanner(System.in);
			String pass=null;
			do {
				System.out.println("Old password: ");
				pass=sc.nextLine();
				if(pass.equals(this.password)) {
					System.out.println("Set new password");
					enterPassword();
					System.out.println("Your password has been changed.");
					break;
				}
			}while(!pass.equals(this.password));
			sc.close();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("First name: "+firstName+"\n");
		sb.append("Last name: "+lastName+"\n");
		sb.append("Email address: "+email+"\n");
		sb.append("Mobile phone: "+phone+"\n");
		sb.append("Gender: "+gender+"\n");
		sb.append("===Addresses===\n");
		sb.append(addresses);
		
		return sb.toString();
	}
	
}
	
