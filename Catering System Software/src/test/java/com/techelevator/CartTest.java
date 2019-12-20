package com.techelevator;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.techelevator.items.Item;
import com.techelevator.items.ProductInfo;

public class CartTest {
	private Cart cart;
	private List<ProductInfo> productInfos;
	
	@Before
	public void setup() {
		cart = new Cart();
		productInfos = new ArrayList<ProductInfo>();
		
		productInfos.add(new ProductInfo("B1", new Item("Soda", 1.50), 50, "B"));
		productInfos.add(new ProductInfo("A1", new Item("Tropical Fruit Bowl", 3.5), 50, "A"));
	}
	
	@Test
	public void valid_product_code_returns_correct_product_info() {
		ProductInfo result = cart.isValidProductCode("B1", productInfos);
		Assert.assertEquals(productInfos.get(0), result);
	}
	
	@Test
	public void invalid_product_code_returns_null() {
		ProductInfo result = cart.isValidProductCode("efg1", productInfos);
		Assert.assertEquals(null, result);
	}
	
	@Test
	public void empty_product_code_returns_null() {
		ProductInfo result = cart.isValidProductCode("", productInfos);
		Assert.assertEquals(null, result);
	}
	
	@Test
	public void amount_of_inventory_less_than_amount_requested_returns_false() {
		boolean result = cart.addToCart(55, productInfos.get(0));
		Assert.assertFalse(result);
	}
	
	@Test
	public void amount_of_inventory_greater_than_amount_requested_returns_true() {
		boolean result = cart.addToCart(5, productInfos.get(0));
		Assert.assertTrue(result);
	}
	
	@Test
	public void amount_of_inventory_equals_amount_requested_returns_true() {
		boolean result = cart.addToCart(50, productInfos.get(0));
		Assert.assertTrue(result);
	}
	
	@Test
	public void negative_amount_requested_returns_false() {
		boolean result = cart.addToCart(-55, productInfos.get(0));
		Assert.assertFalse(result);
	}
}
