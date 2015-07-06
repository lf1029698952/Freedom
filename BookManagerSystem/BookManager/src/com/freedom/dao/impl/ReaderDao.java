package com.freedom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.freedom.bean.Book;
import com.freedom.bean.Reader;

public class ReaderDao extends HibernateDaoSupport {

	// ��ҳ��ѯ
	public List<Reader> query(int pageSize, int page) {

		this.getHibernateTemplate().loadAll(Reader.class);
		String hql = "from Reader";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((page - 1) * pageSize);// ��һ����¼������
		query.setMaxResults(pageSize);
		List<Reader> list = (List<Reader>) query.list();
		session.close();

		return list;
	}

	public int queryCount(int pageSize) {

		String hql = "from Reader";
		@SuppressWarnings("unchecked")
		List<Book> list = this.getHibernateTemplate().find(hql);
		int count = list.size();
		return (int) Math.ceil((count / pageSize)) + 1;
	}

	public void add(Reader reader) {

		this.getHibernateTemplate().save(reader);
	}
}
