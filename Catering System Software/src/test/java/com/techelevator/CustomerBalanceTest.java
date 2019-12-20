package com.techelevator;

import org.junit.*;

import com.techelevator.CustomerBalance;

public class CustomerBalanceTest {
	CustomerBalance customerBalanceTest;
	
	@Before
	public void setup() {
		customerBalanceTest = new CustomerBalance();
	}

	@Test
	public void balance_over_5000_returns_invalid() {
		boolean moneyAdded = customerBalanceTest.addMoney(5001);
		Assert.assertEquals(false, moneyAdded);
	}
	
	@Test
	public void can_add_as_long_as_balance_under_5000() {
		boolean tryOne = customerBalanceTest.addMoney(2);
		Assert.assertTrue(tryOne);
		double testBalance = customerBalanceTest.getBalance();
		Assert.assertEquals(2, testBalance, 0.005);
	}
	
	@Test
	public void adding_balance_to_over_5000_returns_original_balance() {
		boolean result = customerBalanceTest.addMoney(1000);
		Assert.assertEquals(1000, customerBalanceTest.getBalance(), 0.005);
		Assert.assertTrue(result);
		
		result = customerBalanceTest.addMoney(6000);
		Assert.assertEquals(1000, customerBalanceTest.getBalance(), 0.005);
		Assert.assertFalse(result);
	}
	
	@Test
	public void adding_negative_returns_original_balance() {
		boolean result = customerBalanceTest.addMoney(-10);
		Assert.assertFalse(result);
		Assert.assertEquals(0, customerBalanceTest.getBalance(), 0.005);
	}
	
	@Test
	public void subtracting_negative_returns_original_amount() {
		boolean result = customerBalanceTest.subtractMoney(-10);
		Assert.assertFalse(result);
		Assert.assertEquals(0, customerBalanceTest.getBalance(), 0.005);
	}
	
	@Test
	public void subtracting_more_than_balance_returns_original_amount() {
		boolean result = customerBalanceTest.subtractMoney(10);
		Assert.assertFalse(result);
		Assert.assertEquals(0, customerBalanceTest.getBalance(), 0.005);
	}
	
}
