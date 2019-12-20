package com.techelevator.inventory;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.items.Appetizer;
import com.techelevator.items.Beverage;
import com.techelevator.items.Dessert;
import com.techelevator.items.Entree;
import com.techelevator.items.Item;
import com.techelevator.items.ProductInfo;

public class InventoryPopulator {
	
	private List<String> inputLines;
	private final static int INITIAL_STOCKED_AMOUNT = 50;
	
	public InventoryPopulator(CsvInventoryReader csvInventoryReader) {
		inputLines = csvInventoryReader.read();
	}
	
	public List<ProductInfo> populateListOfProductInfos() {
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>();
		for (String inputLine : inputLines) {
			String[] splitInputLine = inputLine.split("\\|");
			ProductInfo productInfo = createProductInfoFromParts(splitInputLine);
			productInfos.add(productInfo);
		}
		return productInfos;
	}
	
	private ProductInfo createProductInfoFromParts(String[] parts) {
		
		Item item = null;
		double price = Double.parseDouble(parts[2]);
		String productType = parts[3];
		String productCode = parts[0];
		
		switch (productType) {
			case "B":
				productType = "Beverage";
				item = new Beverage(parts[1], price);
				break;
			case "A":
				productType = "Appetizer";
				item = new Appetizer(parts[1], price);
				break;
			case "E":
				productType = "Entree";
				item = new Entree(parts[1], price);
				break;
			case "D":
				productType = "Dessert";
				item = new Dessert(parts[1], price);
				break;
		}
		
		ProductInfo productInfo = new ProductInfo(productCode, item, INITIAL_STOCKED_AMOUNT, productType);
		return productInfo;		
	}
}
