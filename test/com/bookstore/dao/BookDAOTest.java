package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {
	private static BookDAO bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(1);
		
		Category category = new Category("Java");
		category.setCategoryId(2);
		existBook.setCategory(category);
		
		existBook.setTitle("Effective Java-Third Edition");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("Many new items have been added, including a chapter devoted to lambdas and streams.");
		existBook.setPrice(3191.00f);
		existBook.setIsbn("9549237947378");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("12/18/2017");
		existBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\messysaw\\Documents\\Mini project\\Pictures\\Book Categories\\Java\\#3 Java.jpg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		
		Book updatedBook = bookDao.update(existBook);
		
		assertEquals(updatedBook.getTitle(), "Effective Java-Third Edition");
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		
		Category category = new Category("Java");
		category.setCategoryId(2);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective Java-Third Edition");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("Many new items have been added, including a chapter devoted to lambdas and streams.");
		newBook.setPrice(3191.00f);
		newBook.setIsbn("9549237947378");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("12/18/2017");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\messysaw\\Documents\\Mini project\\Pictures\\Book Categories\\Java\\#3 Java.jpg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDao.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDao.delete(bookId);
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId = 99;
		Book book = bookDao.get(bookId);
		
		assertNull(book);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 3;
		Book book = bookDao.get(bookId);
		
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDao.listAll();
		
		for (Book aBook : listBooks) {
			System.out.println(aBook.getTitle() + " - " + aBook.getAuthor());
		}
		
		assertFalse(listBooks.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinkin in Java";
		Book book = bookDao.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title = "A Textbook for Java Programming";
		Book book = bookDao.findByTitle(title);
		
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
		
		assertNotNull(book);
	}
	
	@Test
	public void testCount() {
		long totalBooks = bookDao.count();
		
		assertEquals(2, totalBooks);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 4;
		bookDao.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDao.listNewBooks();
		for (Book aBook : listNewBooks) {
			System.out.println(aBook.getTitle() + " - " + aBook.getPublishDate());
		}
		assertEquals(4, listNewBooks.size());
	}
	
	@Test
	public void testSearchBookInTitle(){
	  String keyword= "Java";
	  List<Book> result = bookDao.search(keyword);
	  for(Book aBook: result) {
	      System.out.println(aBook.getTitle());
	  }
	  assertEquals(6,result.size());
	}
	
	@Test
	public void testSearchBookInAuthor(){
	  String keyword= "Joshua Bloch";
	  List<Book> result = bookDao.search(keyword);
	  for(Book aBook: result) {
	      System.out.println(aBook.getTitle());
	  }
	  assertEquals(1,result.size());
	}
	
	@Test
	public void testSearchBookInDescription(){
	  String keyword= "Nutshell";
	  List<Book> result = bookDao.search(keyword);
	  for(Book aBook: result) {
	      System.out.println(aBook.getTitle());
	  }
	  assertEquals(1,result.size());
	}
	
	@Test
	public void testListByCategory() {
		Integer categoryId = 2;
		
		List<Book> listBooks = bookDao.listByCategory(categoryId);
		
		assertTrue(listBooks.size() > 0);
	}
}