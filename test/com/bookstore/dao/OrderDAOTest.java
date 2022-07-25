package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {

	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(4);
		
		order.setCustomer(customer);
		order.setRecipientName("Pushpalatha");
		order.setRecipientPhoneNo("9827362860");
		order.setShippingAddress("120, Tellus Avenue");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(2);
		orderDetail.setBook(book);
		
		OrderDetailId orderDetailId = new OrderDetailId();
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(82.0f);
		orderDetailId.setBookOrder(order);
		
		orderDetail.setId(orderDetailId);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		
		BookOrder savedOrder = orderDAO.create(order);
		
		assertNotNull(savedOrder);
	}

	@Test
	public void testUpdateBookOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
