package com.freedom.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.freedom.bean.User;
import com.freedom.dao.IBaseDao;

public class BaseDao extends HibernateDaoSupport implements IBaseDao{

	@Override
	public boolean save() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	public boolean query(String username) {
		
		Criteria c = this.getSessionFactory().openSession().createCriteria(User.class);
		c.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<User> user = c.list();
		
		if (user.size()>0) {
			return false;
		} else {
			return true;
		}
	}

}
