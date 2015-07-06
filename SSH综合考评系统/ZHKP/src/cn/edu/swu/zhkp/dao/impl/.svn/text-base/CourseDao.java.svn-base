package cn.edu.swu.zhkp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.Course;

public class CourseDao extends BaseDAO<Course> implements QueryDAO<Course> {

	@Override
	public int getRows(String fieldname, String value) {
		String hql = "";
		if (fieldname != null && !"".equals(fieldname)) {
			hql = "select count(*) From Course where Course." + fieldname
					+ "='" + value + "'";
		} else {
			hql = "select count(*) From Course";
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
	public List<Course> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		String hql = "";
		List<Course> courses = new ArrayList<Course>();

		if (fieldname != null && !"".equals(fieldname)) {
			hql = "From Course where Course." + fieldname + "='" + value;
		} else {
			hql = "From Course";
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		courses = query.list();
		return courses;
	}

	@Override
	public Course queryById(long id) {
		return this.getHibernateTemplate().get(Course.class, id);
	}
}
