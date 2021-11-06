package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

public class BookServices {
	private BookDAO bookDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private EntityManager entityManager;
	
	public BookServices(HttpServletRequest request, HttpServletResponse response, EntityManager entityManager) {
		super();
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		bookDAO = new BookDAO(entityManager);
	}

	public void listBooks() throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		
		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
}
