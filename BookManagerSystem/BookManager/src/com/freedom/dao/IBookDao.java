package com.freedom.dao;

import java.util.List;

import com.freedom.bean.Book;

public interface IBookDao {
	List<Book> query(int pageSize, int page);
	void add(Book book);
	boolean delete();
	int queryCount(int pageSize);
	boolean update(Book book, int bookno);
}
