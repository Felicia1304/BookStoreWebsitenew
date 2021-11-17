package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer(){
	     Customer customer = new Customer();
	     customer.setEmail("latha@gmail.com");
	     customer.setFullName("Latha");
	     customer.setCity("Tambaram");
	     customer.setCountry("India");
	     customer.setAddress("120, North Avenue");
	     customer.setPassword("hello");
	     customer.setPhoneNo("982736286");
	     customer.setZipCode("600074");

	     Customer savedCustomer = customerDAO.create(customer);

	     assertTrue(savedCustomer.getCustomerId() > 0);
	}
	
	@Test
	public void testGet() {
	  Integer customerId = 1;
	  Customer customer = customerDAO.get(customerId);

	  assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
	   Customer customer = customerDAO.get(1);
	   String fullName = "Felicia Grace";
	   customer.setFullName(fullName);

	   Customer updatedCustomer = customerDAO.update(customer);
	    
	   assertTrue(updatedCustomer.getFullName().equals(fullName));
	}

	@Test
	public void testDeleteCustomer() {
	   Integer customerId = 1;
	   customerDAO.delete(customerId);
	   Customer customer = customerDAO.get(1);

	   assertNull(customer);
	}

	@Test
	public void testListAll(){
	    List<Customer> listCustomers = customerDAO.listAll();
	   
	    for(Customer customer : listCustomers){
	      System.out.println(customer.getFullName());
	    }
	  
	    assertFalse(listCustomers.isEmpty());
	}

	@Test
	public void testCount() {
	   long totalCustomers = customerDAO.count();
	   assertEquals(2, totalCustomers);
	}

	@Test
	public void testFindByEmail() {
		String email = "latha@gmail.com";
		Customer customer = customerDAO.findByEmail(email);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "latha@gmail.com";
		String password = "hello";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email = "Mary@gmail.com";
		String password = "hello";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNull(customer);
	}
	
}
