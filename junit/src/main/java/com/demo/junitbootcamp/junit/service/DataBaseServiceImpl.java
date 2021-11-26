package com.demo.junitbootcamp.junit.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.junitbootcamp.junit.model.Product;

@Service
public class DataBaseServiceImpl implements DataBaseServiceI {
	
	private Map<Integer,Product> storage = new HashMap<>();

	@Override
	public void initBBDD() {
		storage.put(1, new Product("Jeans", 35.99D));
		storage.put(2, new Product("Shirt", 30.00D));
		storage.put(3, new Product("Fedora", 59.99D));
		
	}

	@Override
	public Product findProductById(Integer id) {
		return storage.get(id);
	}

	@Override
	public Integer insertProduct(Product product) {
		storage.put(storage.size()+1, product);
		return storage.size();
		
	}

}
