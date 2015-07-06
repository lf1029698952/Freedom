package cn.edu.swu.zhkp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.User;

public class UserDao extends BaseDAO<User> implements QueryDAO<User> {

	public User getUserBySchoolNum(String schoolnum) {
		Criteria c = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(User.class);
		c.add(Expression.eq("schoolnum", schoolnum));
		List<User> list = c.list();
		if (list == null) {
			return null;
		}
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int getRows(String fieldname, String value) {
		String hql = "";
		if (fieldname != null && !"".equals(fieldname)) {
			hql = "select count(*) From User where User." + fieldname + "='"
					+ value + "'";
		} else {
			hql = "select count(*) From User";
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Long l = (Long) (session.createQuery(hql).uniqueResult());
		return l.intValue();
	}

	@Override
	public int getMaxID() {
		return 0;
	}

	@Override
	public List<User> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		String hql = "";
		List<User> users = new ArrayList<User>();

		if (fieldname != null && !"".equals(fieldname)) {
			hql = "From User where User." + fieldname + "='" + value;
		} else {
			hql = "From User";
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		users = query.list();
		return users;
	}

	@Override
	public User queryById(long id) {
		return this.getHibernateTemplate().get(User.class, id);
	}
}
