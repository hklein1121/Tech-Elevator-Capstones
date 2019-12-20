package com.techelevator;
import org.junit.*;

public class ChangeMakerTest {
	
	ChangeMaker changeMaker;
	@Before
	public void setup() {
		changeMaker = new ChangeMaker();
	}

	@Test
	public void amount_74_65_returns_correctly() {
		System.out.println("$74.65");
		changeMaker.makeChange(74.65);
	}
	
	@Test 
	public void negative_numbers_return_zero() {
		System.out.println("$-1");
		changeMaker.makeChange(-1);
	}
}
