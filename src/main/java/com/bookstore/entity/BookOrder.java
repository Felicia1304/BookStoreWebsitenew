package com.bookstore.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "book_order", catalog = "bookstoredb")
@NamedQueries({
	@NamedQuery(name = "BookOrder.findAll", query = "SELECT bo FROM BookOrder bo ORDER BY bo.orderDate DESC"),
	@NamedQuery(name = "BookOrder.countAll", query = "SELECT COUNT(*) FROM BookOrder"),
	@NamedQuery(name = "BookOrder.findByCustomer", query = "SELECT bo FROM BookOrder bo WHERE bo.customer.customerId =:customerId ORDER BY bo.orderDate DESC"),
	@NamedQuery(name = "BookOrder.findByIdAndCustomer", query = "SELECT bo FROM BookOrder bo WHERE bo.orderId =:orderId AND bo.customer.customerId =:customerId")
})
public class BookOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Customer customer;
	private Date orderDate;
	private String shippingAddress;
	private String recipientName;
	private String recipientPhoneNo;
	private String paymentMethod;
	private float orderTotal;
	private String orderStatus;
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public BookOrder() {
	}

	public BookOrder(Customer customer, Date orderDate, String shippingAddress, String recipientName,
			String recipientPhoneNo, String paymentMethod, float orderTotal, String orderStatus) {
		//this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.recipientName = recipientName;
		this.recipientPhoneNo = recipientPhoneNo;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
	}

	public BookOrder(Customer customer, Date orderDate, String shippingAddress, String recipientName,
			String recipientPhoneNo, String paymentMethod, float orderTotal, String orderStatus, Set<OrderDetail> orderDetails) {
		//this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.recipientName = recipientName;
		this.recipientPhoneNo = recipientPhoneNo;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
		this.orderDetails = orderDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false, length = 19)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "shipping_address", nullable = false, length = 256)
	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Column(name = "recipient_name", nullable = false, length = 30)
	public String getRecipientName() {
		return this.recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	@Column(name = "recipient_phone_no", nullable = false, length = 15)
	public String getRecipientPhoneNo() {
		return this.recipientPhoneNo;
	}

	public void setRecipientPhoneNo(String recipientPhoneNo) {
		this.recipientPhoneNo = recipientPhoneNo;
	}

	@Column(name = "payment_method", nullable = false, length = 20)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "order_total", nullable = false, precision = 12, scale = 0)
	public float getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	@Column(name = "order_status", nullable = false, length = 20)
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bookOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Transient
	public int getBookCopies() {
		int total = 0;
		
		for (OrderDetail orderDetail : orderDetails) {
			total += orderDetail.getQuantity();
		}
		
		return total;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookOrder other = (BookOrder) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}
}
