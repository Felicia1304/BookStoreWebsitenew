package com.bookstore.entity;
// Generated Oct 11, 2021, 10:02:13 PM by Hibernate Tools 5.5.7.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({
	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c ORDER BY c.registerDate DESC"),
	@NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
	@NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c.email) FROM Customer c"),
	@NamedQuery(name = "Customer.checkLogin", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :pass")
})
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int customerId;
	private String email;
	private String fullName;
	private String address;
	private String city;
	private String country;
	private int phoneNo;
	private String zipCode;
	private String password;
	private Date registerDate;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<BookOrder> bookOrders = new HashSet<BookOrder>(0);

	public Customer() {
	}

	public Customer(int customerId, String email, String fullName, String address, String city, String country,
			int phoneNo, String zipCode, String password, Date registerDate) {
		this.customerId = customerId;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNo = phoneNo;
		this.zipCode = zipCode;
		this.password = password;
		this.registerDate = registerDate;
	}

	public Customer(int customerId, String email, String fullName, String address, String city, String country,
			int phoneNo, String zipCode, String password, Date registerDate, Set<Review> reviews, Set<BookOrder> bookOrders) {
		this.customerId = customerId;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNo = phoneNo;
		this.zipCode = zipCode;
		this.password = password;
		this.registerDate = registerDate;
		this.reviews = reviews;
		this.bookOrders = bookOrders;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fullname", nullable = false, length = 30)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "address", nullable = false, length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city", nullable = false, length = 32)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = false, length = 64)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "phone", nullable = false, length = 15)
	public int getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "zipcode", nullable = false, length = 24)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", nullable = false, length = 19)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<BookOrder> getBookOrders() {
		return this.bookOrders;
	}

	public void setBookOrders(Set<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}

}
