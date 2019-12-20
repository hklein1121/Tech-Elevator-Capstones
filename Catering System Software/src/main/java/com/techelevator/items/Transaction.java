package com.techelevator.items;

public class Transaction {

	private Item item;
	private int itemsPurchased;
	private String productCode;
	private String productType;
	
	public String getProductType() {
		return productType;
	}

	public Transaction(String productCode, Item item, int itemsPurchased, String productType) {
		this.productCode = productCode;
		this.item = item;
		this.itemsPurchased = itemsPurchased;
		this.productType = productType;
	}

	public String getProductCode() {
		return productCode;
	}

	public Item getItem() {
		return this.item;
	}
	
	public int getItemsPurchased() {
		return this.itemsPurchased;
	}
	
	public boolean isSoldOut() {
		return itemsPurchased < 1;
	}	
}
