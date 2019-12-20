package com.techelevator.items;

public class ProductInfo {

	private Item item;
	private int stock;
	private String productCode;
	private String productType;
	
	public String getProductType() {
		return productType;
	}

	public ProductInfo(String productCode, Item item, int stock, String productType) {
		this.productCode = productCode;
		this.item = item;
		this.stock = stock;
		this.productType = productType;
	}

	public String getProductCode() {
		return productCode;
	}

	public Item getItem() {
		return this.item;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void decrementStock(int amountToSubtract) {
		stock = stock - amountToSubtract;
	}
	
	public boolean isSoldOut() {
		return stock < 1;
	}	
}
