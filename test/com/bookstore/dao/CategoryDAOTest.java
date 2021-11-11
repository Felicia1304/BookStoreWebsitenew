package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	
	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDAO = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Historical");
		Category category = categoryDAO.create(newCat);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("11th CBSE");
		cat.setCategoryId(3);
		
		Category category = categoryDAO.update(cat);
		
		assertEquals(cat.getCname(), category.getCname());
	}

	@Test
	public void testGet() {
		Integer catId = 23;
		Category cat = categoryDAO.get(catId);
		
		assertNotNull(cat);
	}
	

	@Test
	public void testDeleteCategory() {
		Integer catId = 3;
		categoryDAO.delete(catId);
		
		Category cat = categoryDAO.get(catId);
		
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDAO.listAll();
		
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDAO.count();
		
		assertTrue(totalCategories > 0);
	}

	@Test
	public void testFindByName() {
		String name = "Java";
		Category category = categoryDAO.findByName(name);
		
		assertNotNull(category);
	}
	
	@Test
	public void testFindByNameNotFound() {
		String name = "JavaCore";
		Category category = categoryDAO.findByName(name);
		
		assertNull(category);
	}
	
}
