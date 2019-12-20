package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import com.techelevator.items.ProductInfo;

public class LogFiler {
	
	private Calendar calendar;
	File outputFile = new File("Log.txt");
	
	public void moneyAddedLogger(int moneyAdded, double currentBalance) {
		String str = "";
		str += getDateAndTime();
		str += "ADD MONEY: ";
		
		try (PrintWriter printWriter = new PrintWriter(new FileOutputStream (outputFile, true))) {
			
			printWriter.printf(str + "$%s $%.2f %n", moneyAdded , currentBalance);

		} catch (FileNotFoundException e) {
			System.out.println("woops!");
			System.exit(1);
		}
				
	}
	
	public void giveChangeLogger(double change, double currentBalance) {
		String str = "";
		str += getDateAndTime();
		str += "GIVE CHANGE: ";
		
		try (PrintWriter printWriter = new PrintWriter(new FileOutputStream (outputFile, true))) {
			
			printWriter.printf(str + "$%.2f $%.2f %n", change , currentBalance);

		} catch (FileNotFoundException e) {
			System.out.println("woops!");
			System.exit(1);
		}
		
	}
	
	public void purchaseLogger(int numberOfItems, ProductInfo productInfo, double totalCostOfItems, double currentBalance) {
		String str = "";
		str += getDateAndTime();
		
		try (PrintWriter printWriter = new PrintWriter(new FileOutputStream (outputFile, true))) {
			
			printWriter.printf(str + "%d %s %s $%.2f $%.2f %n", numberOfItems, productInfo.getItem().getName(), productInfo.getProductCode(), totalCostOfItems , currentBalance);

		} catch (FileNotFoundException e) {
			System.out.println("woops!");
			System.exit(1);
		}
		
	}

	private String getDateAndTime() {
		String str = "";
		calendar = Calendar.getInstance();
	
		str += (calendar.get(Calendar.MONTH) + 1) + "/";
		str += calendar.get(Calendar.DAY_OF_MONTH) + "/";
		str += calendar.get(Calendar.YEAR) + " ";
		str += calendar.get(Calendar.HOUR_OF_DAY) + ":";
		if(calendar.get(Calendar.MINUTE) < 10) {
			str += 0;
		}
		str += calendar.get(Calendar.MINUTE) + ":";
		if(calendar.get(Calendar.SECOND) < 10) {
			str += 0;
		}
		str += calendar.get(Calendar.SECOND) + " ";
		
		return str;
	}
	
}
