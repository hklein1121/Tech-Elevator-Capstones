package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techelevator.items.Transaction;
import com.techelevator.items.Item;
import com.techelevator.items.ProductInfo;

public class Cart {
	
	private Map<String, Transaction> transactions = new HashMap<String, Transaction>();

	public ProductInfo isValidProductCode(String stringInput, List<ProductInfo> productInfos) {
		
		for (ProductInfo productInfo : productInfos) {
			if (productInfo.getProductCode().equals(stringInput)) {
				return productInfo;
			}
		}
		
		return null;
	}

	public boolean addToCart(int pendingAmountInventoryToAdd, ProductInfo productInfo) {
		if (pendingAmountInventoryToAdd <= productInfo.getStock() && pendingAmountInventoryToAdd > 0) {
			int amountInventoryToAdd = pendingAmountInventoryToAdd;
			String productCode = productInfo.getProductCode();
			if (transactions.containsKey(productCode)) {
				amountInventoryToAdd += transactions.get(productCode).getItemsPurchased();
			}
			
			transactions.put(productCode, new Transaction(productInfo.getProductCode(), productInfo.getItem(), amountInventoryToAdd, productInfo.getProductType()));
			return true;
		}
		
		return false;
	}
	
	public void displayReceipt() {
		double sum = 0;
		for (Map.Entry<String, Transaction> itemBought : transactions.entrySet() ) {
			Transaction transaction = itemBought.getValue();
			Item item = transaction.getItem();
			String productType = transaction.getProductType();
			String name = item.getName();
			double unitPrice = item.getPrice();
			int numberOfItem = transaction.getItemsPurchased();
			double totalCostPerItem = unitPrice * numberOfItem;
			sum += totalCostPerItem;
			
			System.out.printf("%-3d %-10s %-10s $%-5.2f $%-5.2f %n", numberOfItem, productType, name, unitPrice, totalCostPerItem);	
		}	
		System.out.printf("%nTotal: $%.2f" , sum);		
	}
	
	public void clearCart() {
		transactions.clear();
	}
	
	
}
