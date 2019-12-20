package com.techelevator;

public class ChangeMaker {
	
	public void makeChange(double currentBalance) {
		
		if (currentBalance < 0)
		{
			System.out.println("No change");
			return;
		}
		
		int numberOf20s = (int) (currentBalance / 20);
		currentBalance %= 20;
		
		int numberOf10s = (int) (currentBalance / 10);
		currentBalance %= 10;
		
		int numberOf5s = (int) (currentBalance / 5);
		currentBalance %= 5;
		
		int numberOf1s = (int) (currentBalance / 1);
		currentBalance %= 1;
		
		int numberOfQuarters = (int) (currentBalance / 0.25);
		currentBalance %= 0.25;
		
		int numberOfDimes = (int) (currentBalance / 0.10);
		currentBalance %= 0.10;
		
		int numberOfNickels = (int) (currentBalance / 0.05);
		currentBalance %= 0.05;
		
		System.out.println("\n\nChange returned:");
		System.out.println("Number of $20's : " + numberOf20s);
		System.out.println("Number of $10's : " + numberOf10s);
		System.out.println("Number of $5's : " + numberOf5s);
		System.out.println("Number of $1's : " + numberOf1s);
		System.out.println("Number of Quarters : " + numberOfQuarters);
		System.out.println("Number of Dimes : " + numberOfDimes);
		System.out.println("Number of Nickels : " + numberOfNickels);
		
	}

}
