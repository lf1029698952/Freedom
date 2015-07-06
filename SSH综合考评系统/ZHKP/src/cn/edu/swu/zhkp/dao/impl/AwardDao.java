package cn.edu.swu.zhkp.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.Award;

public class AwardDao extends BaseDAO<Award> implements QueryDAO<Award> {
	private SessionFactory sessionFactory;

	@Override
	public int getRows(String fieldname, String value) {
		// TODO Auto-generated method stub
		String hql = "";
		// 如果字段名为空则默认查询所有
		if (fieldname == null || fieldname.equals("") || value == null
				|| value.equals(""))
			hql = "SELECT count(*) FROM Award award ";
		else
			hql = "SELECT count(*) FROM Award award  where award." + fieldname
					+ " like '%" + value + "%'";
		@SuppressWarnings("unchecked")
		// HQL用法
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery(hql);
		long count = (long) q.uniqueResult();
		return (int) count;
		// template用法
		// List<Integer> list = this.getHibernateTemplate().find(hql);
		// return list.get(0);
	}

	@Override
	public int getMaxID() {
		String sql = "select max(ID)+1 from Award";
		List list1 = (List) this.getHibernateTemplate().find(sql);
		Iterator it = list1.iterator();
		int maxid = 1;
		if (it.hasNext()) {
			Object o = it.next();
			if (o != null) {
				maxid = (int) o;
			}
		}
		return maxid;
	}

	@Override
	public List<Award> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		final int pageSize1 = pageSize;
		final int startRow1 = startRow;
		final String queryName = fieldname;
		final String queryValue = value;
		String sql = "";

		if (queryName == null || queryName.equals("") || queryValue == null
				|| queryValue.equals(""))
			sql = "FROM Award";
		else
			sql = "FROM Award where " + fieldname + " like '%" + value + "%'";
		final String sql1 = sql;

		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public List doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(sql1);
				query.setFirstResult(startRow1);
				query.setMaxResults(pageSize1);
				return query.list();
			}
		});
	}

	@Override
	public Award queryById(long id) {
		return (Award) this.getHibernateTemplate().get(Award.class, id);
	}

	@Override
	public void save(Award award) {
		this.getHibernateTemplate().save(award);
	}

	@Override
	public void delete(Award award) {
		this.getHibernateTemplate().delete(award);
	}

	@Override
	public void update(Award award) {
		this.getHibernateTemplate().update(award);
	}

	/***************** 自行添加 *******************/
	/*
	 * 获取全部满足完全查询条件的加分申请
	 */
	public List<Award> getAllAwards(String fieldname, String value) {
		final String queryName = fieldname;
		final String queryValue = value;
		String sql = "";
		if (queryName == null || queryName.equals("") || queryValue == null
				|| queryValue.equals(""))
			sql = "FROM Award";
		else
			sql = "FROM Award award where award." + fieldname + "=" + value
					+ "'";
		final String sql1 = sql;
		return this.getHibernateTemplate().find(sql1);
	}

	/*
	 * 获取单个用户某个班级（学年）的加分申请
	 */
	public List<Award> getAllAwardsByUserAndGather(long userId, long gatherId) {
		String sql = "FROM Award award where award.user.id='" + userId
				+ "'award.gather.id=" + gatherId + "'";
		return this.getHibernateTemplate().find(sql);
	}

}
