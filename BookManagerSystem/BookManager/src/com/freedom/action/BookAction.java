package com.freedom.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.freedom.bean.Book;
import com.freedom.dao.impl.BookDao;

public class BookAction {
	
	Book book;
	BookDao bookDao;
	
	private int page = 1;
	private int pageSize = 3;
	private int totalPage = 1;
	private int bookno;
	
	public int getBookno() {
		return bookno;
	}


	public void setBookno(int bookno) {
		this.bookno = bookno;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public Book getBook() {
		return book;
	}
	
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public BookDao getBookDao() {
		return bookDao;
	}


	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}


	public String show() {
		List<Book> list = bookDao.query(pageSize, page);
		System.out.println(list);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		totalPage = bookDao.queryCount(pageSize);
		request.setAttribute("totalPage", totalPage);
		
		return "success";
	}
	
	public String add() {
		
		Book newBook = new Book(book.getType(), book.getName(), book.getAuthor(), book.getPress(), book.getIsbn(), book.getPrice(), book.getNumber(), book.getTime(), book.getRemark());
		
		bookDao.add(newBook);
		
		return "success";
	}
	
	public String updateBook() throws Exception {
	    
	    	this.book.setBookno(bookno);
		bookDao.update(this.book);
		
		return "success";
	}
	
	public String getBookInfo() throws Exception {
		Book book = bookDao.queryBookById(bookno);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("book", book);
	
		return "success";
	}
	
	public String deleteBook() throws Exception {
		
		bookDao.delete(book, bookno);
		
		return "success";
	}
}
