package com.bookstore.entity;

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


@Entity
@Table(name = "category", catalog = "bookstoredb")
@NamedQueries({
	@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c ORDER BY c.cname"),
	@NamedQuery(name = "Category.countAll", query = "SELECT COUNT(*) FROM Category"),
	@NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.cname = :cname")
})
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private String cname;
	private Set<Book> books = new HashSet<Book>(0);
	
	public Category() {
	}

	public Category(String name) {
		this.cname = name;
	} 
	
	public Category(String name, Set<Book> books) {
		this.cname = name;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "cname", length = 30)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
