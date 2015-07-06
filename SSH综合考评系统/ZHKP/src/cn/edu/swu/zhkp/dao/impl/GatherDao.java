package cn.edu.swu.zhkp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.Admin;
import cn.edu.swu.zhkp.entity.Gather;

public class GatherDao extends BaseDAO<Gather> implements QueryDAO<Gather> {

	@Override
	public int getRows(String fieldname, String value) {
		String hql = "";
		if (fieldname != null && !"".equals(fieldname)) {
			hql = "select count(*) From Gather where Gather." + fieldname
					+ "='" + value + "'";
		} else {
			hql = "select count(*) From Gather";
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Long l = (Long) (session.createQuery(hql).uniqueResult());
		return l.intValue();
	}

	public int getRows(Admin admin) {
		String hql = "";
		if (admin.getRole().getId() == 1) {
			hql = "select count(*) From Gather";
		} else {
			hql = "select count(*) From Gather where Gather.admin.id="
					+ admin.getId();
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
	public List<Gather> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		String hql = "";
		List<Gather> gathers = new ArrayList<Gather>();

		if (fieldname != null && !"".equals(fieldname)) {
			hql = "From Course where Gather." + fieldname + "='" + value;
		} else {
			hql = "From Gather";
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		gathers = query.list();
		return gathers;
	}

	@Override
	public Gather queryById(long id) {
		return this.getHibernateTemplate().get(Gather.class, id);
	}

	/**
	 * 通过用户获取Gather.如果是超级管理员就可以直接获取全部的用户
	 * 
	 * @param pageSize
	 * @param startRow
	 * @return
	 */
	public List<Gather> getDatasByAdmin(int pageSize, int startRow, Admin admin) {
		String hql = "";
		List<Gather> gathers = new ArrayList<Gather>();
		if (admin.getRole().getId() == 1) {
			hql = "From Gather";
		} else {
			hql = "From Gather where Gather.admin.id=" + admin.getId();
		}
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		gathers = query.list();
		return gathers;
	}
}
