package com.techelevator;

import java.util.List;

import com.techelevator.inventory.InventoryPopulator;
import com.techelevator.items.ProductInfo;

public class InventoryTracker {
	
	private List<ProductInfo> productInfos;
	private InventoryPopulator inventoryPopulator;
	
	public InventoryTracker(InventoryPopulator inventoryPopulator) {
		this.inventoryPopulator = inventoryPopulator;
	}
	
	public List<ProductInfo> getProductInfos() {
		productInfos = inventoryPopulator.populateListOfProductInfos();
		return productInfos;
	}
}
