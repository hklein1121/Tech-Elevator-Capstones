package com.techelevator.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvInventoryReader implements InventoryReader {

	private String fileName;
	
	public CsvInventoryReader(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public List<String> read() {
		
		List<String> inputLines = new ArrayList<String>();
		
		File file = new File(fileName);
		
		try (Scanner fileScanner = new Scanner(file)) {
			
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				inputLines.add(line);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		return inputLines;
	}
}
