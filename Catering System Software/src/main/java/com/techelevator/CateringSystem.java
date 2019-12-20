package com.techelevator;

import java.util.List;

import com.techelevator.items.ProductInfo;

public class CateringSystem {

	private InventoryTracker inventoryTracker;
	private CustomerBalance customerBalance;
	private Cart cart;
	
	public CateringSystem(InventoryTracker inventoryTracker) {
		this.inventoryTracker = inventoryTracker;
		this.customerBalance = new CustomerBalance();
		this.cart = new Cart();
	}
	
	public List<ProductInfo> getItems() {
		return inventoryTracker.getProductInfos();
	}
	
	public CustomerBalance getCustomerBalance() {
		return customerBalance;
	}
	
	public Cart getCart() { 
		return cart;
	}
}
