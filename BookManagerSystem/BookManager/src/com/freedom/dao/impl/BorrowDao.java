package com.freedom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.freedom.bean.Book;
import com.freedom.bean.Borrow;

public class BorrowDao extends HibernateDaoSupport{
	
	public boolean borrow(Borrow borrow, int bookno) {
		
	    	//先判断是否有此书
		String hql = "from Book where bookno = '"+bookno+"'";
		List list = this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			this.getHibernateTemplate().save(borrow);
			return true;
		}else {
			return false;
		}
	
	}
	
	public boolean returnBook(Borrow borrow, int bookno, int readerno) {
		
		/*String hql = "from Borrow where bookno = '"+bookno+"'";
		List list = this.getHibernateTemplate().find(hql);
		if (list.size()>0) {
			this.getHibernateTemplate().update(borrow);
			return true;
		} else {
			return false;
		}*/
	    	
	    	//先判断此书是否被借
        	Session session = this.getSession();
        	session.beginTransaction();
        	String hql = "from Borrow borrow where borrow.book.bookno = ? and borrow.reader.readerno = ?";
        	Query query = session.createQuery(hql);
        	query.setInteger(0, bookno);
        	query.setInteger(1, readerno);
        	List<Borrow> list = query.list();
        	if (list.size() <= 0) {
        	    return false;
        	}
        	
        	Borrow borrow1 = list.get(0);
        	borrow1.setRtime(borrow.getRtime());
        	System.out.println(borrow.getRtime());
        	session.update(borrow1);
        	session.getTransaction().commit();
        	
        	return true;
		
	}
	
	public List borrowed() {
		
		String hql = "from Borrow borrow where borrow.rtime is null or borrow.rtime = ''";
		List list = this.getHibernateTemplate().find(hql);
		
		return list;
	}
	
	public List history() {
		
		String hql = "from Borrow";
		List list = this.getHibernateTemplate().find(hql);
		System.out.println(list.get(0));
		
		return list;
	}
	
}