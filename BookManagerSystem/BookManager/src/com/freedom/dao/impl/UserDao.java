package com.freedom.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.freedom.bean.Book;
import com.freedom.bean.User;
import com.freedom.dao.IUserDao;

public class UserDao extends HibernateDaoSupport implements IUserDao{

	@Override
	public boolean login(String username, String password) {
		Criteria c = this.getSessionFactory().openSession().createCriteria(User.class);
		c.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<User> user = c.list();
		if(user.get(0).getPassword().equals((password))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void register(String username, String password, String realname, String tel) {
		User user = new User(username, password, realname, tel, 0);
		this.getHibernateTemplate().save(user);
	}
	
	    // 分页查询
	    public List<User> query(int pageSize, int page) {

		this.getHibernateTemplate().loadAll(User.class);
		String hql = "from User";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((page - 1) * pageSize);// 第一条记录的索引
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		session.close();

		return list;
	    }
	    
	    public int queryCount(int pageSize) {

		String hql = "from User";
		@SuppressWarnings("unchecked")
		List<Book> list = this.getHibernateTemplate().find(hql);
		int count = list.size();
		return (int) Math.ceil((count/pageSize))+1;
	    }

}
