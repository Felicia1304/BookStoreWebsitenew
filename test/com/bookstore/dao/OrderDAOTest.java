package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
//import com.bookstore.entity.OrderDetailId;

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
		
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(6382.0f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0);
	}
	
	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(6);
		
		order.setCustomer(customer);
		order.setRecipientName("David");
		order.setRecipientPhoneNo("9854674863");
		order.setShippingAddress("124, Park Avenue");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Book book1 = new Book(13);
		orderDetail1.setBook(book1);
		
		orderDetail1.setQuantity(1);
		orderDetail1.setSubtotal(550.0f);
		orderDetail1.setBookOrder(order);
		
		orderDetails.add(orderDetail1);
		
		OrderDetail orderDetail2 = new OrderDetail();
		Book book2 = new Book(17);
		orderDetail2.setBook(book2);
		
		orderDetail2.setQuantity(2);
		orderDetail2.setSubtotal(650.0f);
		orderDetail2.setBookOrder(order);
		
		orderDetails.add(orderDetail2);
		
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2);
	}

	@Test
	public void testUpdateBookOrderShippingAddress() {
		Integer orderId = 1;
		BookOrder order = orderDAO.get(orderId);
		order.setShippingAddress("New Shipping Address");
		
		orderDAO.update(order);
		
		BookOrder updateOrder = orderDAO.get(orderId);
		
		assertEquals(order.getShippingAddress(), updateOrder.getShippingAddress());
	}

	@Test
	public void testUpdateBookOrderDetail() {
		Integer orderId = 2;
		BookOrder order = orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 13) {
				orderDetail.setQuantity(2);
				orderDetail.setSubtotal(1100);
			}
		}
		
		orderDAO.update(order);
		
		iterator = order.getOrderDetails().iterator();
		
		int expectedQuantity = 2;
		float expectedSubtotal = 1100;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId() == 13) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
	}
	
	@Test
	public void testGet() {
		Integer orderId = 1;
		BookOrder order = orderDAO.get(orderId);
		System.out.println(order.getRecipientName());
		System.out.println(order.getRecipientPhoneNo());
		System.out.println(order.getShippingAddress());
		System.out.println(order.getOrderStatus());
		System.out.println(order.getOrderTotal());
		System.out.println(order.getPaymentMethod());
		
		assertEquals(1, order.getOrderDetails().size());
	}
	
	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId = 10;
		Integer customerId = 13;
		
		BookOrder order = orderDAO.get(orderId, customerId);
		
		assertNull(order);
	}
	
	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId = 5;
		Integer customerId = 5;
		
		BookOrder order = orderDAO.get(orderId, customerId);
		
		assertNotNull(order);
	}

	@Test
	public void testDeleteObject() {
		int orderId = 1;
		orderDAO.delete(orderId);
		
		BookOrder order = orderDAO.get(orderId);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> listOrders = orderDAO.listAll();
		
		for (BookOrder order : listOrders) {
			System.out.println(order.getOrderId() + " - " + order.getCustomer().getFullName() + " - " + order.getOrderTotal() + " - " + order.getOrderStatus());
			for (OrderDetail detail : order.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subtotal = detail.getSubtotal();
				System.out.println("\t" + book.getTitle() + " - " + quantity + " - " + subtotal);
			}
		}
		
		assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 10;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 4;
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	}

	@Test
	public void testCount() {
		long totalOrders = orderDAO.count(); 
		assertEquals(2, totalOrders);
	}

	@Test
	public void testListMostRecentSales() {
		List<BookOrder> recentOrders = orderDAO.listMostRecentSales();
		
		assertEquals(3, recentOrders.size());
	}
	
}
