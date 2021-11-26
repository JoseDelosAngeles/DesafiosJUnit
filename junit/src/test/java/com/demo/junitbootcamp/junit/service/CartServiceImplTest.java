package com.demo.junitbootcamp.junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.junitbootcamp.junit.model.Product;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {

	@InjectMocks
	private CartServiceI service = new CartServiceImpl();
	
	@Mock
	private DataBaseServiceI database;
	
//	@BeforeEach
//	public void setUp() throws Exception {
//		System.out.println("Starting service");
//		
//	}

	@Test
	public void testClearCart() {
		System.out.println("Clearing Cart");
		service.addProduct(new Product("Jeans", 35.99D));
		assertFalse(service.getProducts().isEmpty());
		service.clearCart();
		assertTrue(service.getProducts().isEmpty());
	}

	@Test
	public void testAddProduct() {
		System.out.println("Adding Product");
		assertTrue(service.getProducts().isEmpty());
		service.addProduct(new Product("Jeans", 35.99D));
		assertFalse(service.getProducts().isEmpty());
	}

	@Test
	public void testGetProductNumber() {
		System.out.println("Getting Number of Products");
		service.addProduct(new Product("Jeans", 35.99D));
		service.addProduct(new Product("Shirt", 30.99D));
		assertTrue(service.getProductNumber()==2);
		
	}

	@Test
	public void testGetProducts() {
		System.out.println("Getting Products");
		List<Product> list = new ArrayList<>();
		Product p = new Product("Jeans", 35.99D);
		service.addProduct(p);
		list.add(p);
		assertEquals(service.getProducts(), list);
	}

	@Test
	public void testTotalPrice() {
		System.out.println("Total Price");
		service.addProduct(new Product("Jeans", 35.99D));
		service.addProduct(new Product("Shirt", 30.99D));
		assertTrue(service.totalPrice()==66.98D);
	}

	@Test
	public void testDiscountCalculator() {
		System.out.println("Discount Calculator");
		assertTrue(service.discountCalculator(30D, 10D)==27D);
	}
	
	@Test
	public void testApplyDiscount() {
		Product product = new Product("Jacket", 30.00D);
		when(database.findProductById(2)).thenReturn(product);
		Double result = service.applyDiscount(10D, 2);
		assertEquals(result,27D);
		verify(database).findProductById(anyInt());
	}
	
	@Test
	public void testInsertProduct() {
		Product product = new Product("Jacket", 30.00D);
		when(service.insertProduct(product)).thenReturn(1);
		Integer id = service.insertProduct(product);
		assertEquals(id, 1);
		assertTrue(service.getProducts().contains(product));
		verify(database, atLeast(1)).insertProduct(product);
	}

}
