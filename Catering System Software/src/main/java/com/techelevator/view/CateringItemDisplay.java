package com.techelevator.view;

import java.util.List;

import com.techelevator.items.Item;
import com.techelevator.items.ProductInfo;

public class CateringItemDisplay {
	
	private final static String SOLD_OUT = "SOLD OUT";

	public void displayAllCateringMenuItems(List<ProductInfo> productInfos) {

		for (ProductInfo productInfo : productInfos) {

			Item item = productInfo.getItem();
			
			String productCode = productInfo.getProductCode();
			String itemName =  item.getName();
			double itemPrice = item.getPrice();
			int itemStock = productInfo.getStock();
			if (itemStock < 1) {
				System.out.printf("%2s  %-25s  $%-5.2f  %-15s%n", productCode, itemName, itemPrice, SOLD_OUT);
			} else {
				System.out.printf("%2s  %-25s  $%-5.2f  %-15s%n", productCode, itemName, itemPrice, itemStock);
			}
		}
		System.out.println();
	}
}
