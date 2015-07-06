package cn.edu.swu.zhkp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.Admin;

public class AdminDao extends BaseDAO<Admin> implements QueryDAO<Admin> {

	public void addStudents() {
	}

	/**
	 * 通过username得到admin
	 * 
	 * @param username
	 * @return 如果用户名不存在，返回null
	 */
	public Admin getByUsername(String username) {
		Criteria c = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(Admin.class);
		c.add(Expression.eq("username", username));
		List<Admin> list = c.list();
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
			hql = "select count(*) From Admin where Admin." + fieldname + "='"
					+ value + "'";
		} else {
			hql = "select count(*) From Admin";
		}

		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Long l = (Long) (session.createQuery(hql).uniqueResult());
		return l.intValue();
	}

	@Override
	public int getMaxID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Admin> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		String hql = "";
		List<Admin> admins = new ArrayList<Admin>();
		if (fieldname != null && !"".equals(fieldname)) {
			hql = "From Admin where Admin." + fieldname + "='" + value;
		} else {
			hql = "From Admin";
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		admins = query.list();
		return admins;
	}

	@Override
	public Admin queryById(long id) {
		return this.getHibernateTemplate().get(Admin.class, id);
	}

}
