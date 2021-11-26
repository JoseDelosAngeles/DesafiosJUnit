package com.demo.junitbootcamp.junit.service;

import com.demo.junitbootcamp.junit.model.Product;

public interface DataBaseServiceI {
	
	public void initBBDD();
	
	public Product findProductById(Integer id);
	
	public Integer insertProduct(Product product);

}
