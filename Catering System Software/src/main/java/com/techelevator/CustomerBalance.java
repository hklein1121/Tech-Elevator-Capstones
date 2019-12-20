package com.techelevator;

public class CustomerBalance {

	private double balance = 0;
	private ChangeMaker changeMaker;
	private LogFiler logFiler = new LogFiler();
	
	public CustomerBalance() {
		changeMaker = new ChangeMaker();
	}
	
	public boolean addMoney(int addedMoney){
		if (balance + addedMoney > 5000 || addedMoney < 0)
		{
			return false;
		}
		balance = balance + addedMoney;
		logFiler.moneyAddedLogger(addedMoney, balance);

		return true;
	}

	public double getBalance() {
		return balance;
	}
	
	public boolean subtractMoney(double moneyToSubtract) {
		if (moneyToSubtract >= 0 && moneyToSubtract <= balance) {
			balance -= moneyToSubtract;
			return true;
		} else {
			return false;
		}
	}
	
	public void makeChange(double currentBalance) {
		changeMaker.makeChange(currentBalance);
		logFiler.giveChangeLogger(currentBalance, 0);
	}
	
	public void emptyBalance() {
		balance = 0;
	}
}
