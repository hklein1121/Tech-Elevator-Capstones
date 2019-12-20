package com.techelevator.view;

import java.util.InputMismatchException;
import java.util.Scanner;


public class InputOutputMaster{

	private Scanner in = new Scanner(System.in);

	public String getFileName() {
		
		String fileName = "cateringsystem.csv";
		return fileName;
	}
	
	public String readString() {
		String stringInput = in.nextLine();
		
		return stringInput;
	}

	public int readIntegerAmount() {
		int amount = 0;
		while (amount == 0) {
			try {
				amount = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Bad input, please try again.");
			}
			in.nextLine();
		}
		return amount;
	}

	public void displayUserMessage(String message) {
		System.out.println(message);
	}
	
	public void displayMoneyMessage(String message, double amount) {
		System.out.printf(message + "$%.2f" , amount);
	}

	public String getSelectionFromUser(String[] options) {

		String selectedOption = null;

		while (selectedOption == null) {

			int userChoice = 0;

			try {
				userChoice = in.nextInt();
				System.out.println();
			}
			catch (InputMismatchException e) {
				displayUserMessage("Please select a valid option!");
				displayUserMessage("choice >>>");
				in.nextLine();
				continue;
			}
		

			if (userChoice - 1 >= options.length || userChoice < 1) {
				displayUserMessage("Please select a valid option!");
				displayUserMessage("choice >>>");
			} else {
				selectedOption = options[userChoice - 1];
			}
		}
		in.nextLine();
		return selectedOption;

	}

	public void displayUserOptions(String[] options) {
		
		System.out.println();

		for (int i = 0; i < options.length; i++) {
			System.out.println( (i + 1) + ") " + options[i] );
		}
		System.out.print("choice >>>");
	}
}
