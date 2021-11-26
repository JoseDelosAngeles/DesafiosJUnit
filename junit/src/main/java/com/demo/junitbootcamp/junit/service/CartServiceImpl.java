package com.demo.junitbootcamp.junit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.junitbootcamp.junit.model.Product;

@Service
public class CartServiceImpl implements CartServiceI {

	private List<Product> productList = new ArrayList<>();
	
	@Autowired
	private DataBaseServiceI database;
	
	@Override
	public void clearCart() {
		productList.clear();
		
	}

	@Override
	public void addProduct(Product product) {
		productList.add(product);
		
	}

	@Override
	public int getProductNumber() {
		int productNumber = productList.size();
		return productNumber;
	}

	@Override
	public List<Product> getProducts() {
		
		return productList;
	}

	@Override
	public Double totalPrice() {
		Double total = 0d;
		for(Product p : productList) {
			total+=p.getPrice();
		}
		return total;
	}

	//El descuento se añade como tal. 10% sería 10
	@Override
	public Double discountCalculator(double price, double discountPercent) {
		return (price - (price*discountPercent/100));
	}

	@Override
	public Double applyDiscount(Double discount, Integer idProduct) {
		Product product = database.findProductById(idProduct);
		if(product != null) {
			return discountCalculator(product.getPrice(), discount);
		} else {
			System.out.println("Product with id=" + idProduct + " not found");
		}
		return null;
	}

	public Integer insertProduct(Product product) {
		Integer id = database.insertProduct(product);
		addProduct(product);
		return id;
	}
}
