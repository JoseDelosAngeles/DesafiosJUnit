package com.demo.junitbootcamp.junit.service;

import java.util.List;

import com.demo.junitbootcamp.junit.model.Product;

public interface CartServiceI {

	public void clearCart();
	
	public void addProduct(Product product);
	
	public int getProductNumber();
	
	public List<Product> getProducts();
	
	public Double totalPrice();
	
	public Double discountCalculator(double price, double discountPercent);
	
	public Double applyDiscount(Double discount, Integer idProduct);
	
	public Integer insertProduct(Product product);
}
