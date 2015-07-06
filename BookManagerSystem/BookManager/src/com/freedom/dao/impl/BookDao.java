package com.freedom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.freedom.bean.Book;

public class BookDao extends HibernateDaoSupport {

	// 分页查询
	public List<Book> query(int pageSize, int page) {

		this.getHibernateTemplate().loadAll(Book.class);
		String hql = "from Book";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((page - 1) * pageSize);// 第一条记录的索引
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Book> list = (List<Book>) query.list();
		session.close();

		return list;
	}

	public void add(Book book) {
		this.getHibernateTemplate().save(book);
	}

	public boolean update(Book book) {
	    	
	    	//更新对象信息需要先将对象从数据库拿到Session中
		Session session = this.getSession();
		Book book1 = (Book) session.get(Book.class, book.getBookno());
		session.beginTransaction();
		
		book1.setAuthor(book.getAuthor());
		book1.setIsbn(book.getIsbn());
		book1.setName(book.getName());
		book1.setNumber(book.getNumber());
		book1.setPress(book.getPress());
		book1.setPrice(book.getPrice());
		book1.setTime(book.getTime());
		book1.setRemark(book.getRemark());

		session.update(book1);
		session.getTransaction().commit();
		session.close();

		return false;
	}

	public void delete(Book book, int bookno) {

		Session session = this.getSession();
		session.beginTransaction();
		Book book1 = (Book) session.get(Book.class, bookno);
		System.out.println(book1);
		session.delete(book1);
		session.getTransaction().commit();

	}

	public int queryCount(int pageSize) {

		String hql = "from Book";
		@SuppressWarnings("unchecked")
		List<Book> list = this.getHibernateTemplate().find(hql);
		int count = list.size();
		return (int) Math.ceil((count / pageSize)) + 1;
	}

	public Book queryBookById(int bookno) {

		String hql = "from Book book where book.bookno='" + bookno + "'";
		@SuppressWarnings("unchecked")
		List<Book> list = this.getHibernateTemplate().find(hql);
		Book book = list.get(0);

		return book;
	}

}
