package com.techelevator.inventory;

import java.util.List;

import com.techelevator.inventory.exception.InventoryReaderException;

public interface InventoryReader {

	public List<String> read() throws InventoryReaderException;
	
}
