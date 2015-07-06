package cn.edu.swu.zhkp.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.Dept;

/**
 * 部门DAO类
 * 
 * @author Medici.Yan
 *
 */
public class DeptDAO extends BaseDAO<Dept> implements QueryDAO<Dept> {

	public DeptDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * 获取总行数，用于计算分页数
	 * 
	 * @see com.arch.dao.QueryDAO#getRows(java.lang.String, java.lang.String)
	 */
	@Override
	public int getRows(String fieldname, String value) {
		// TODO Auto-generated method stub
		String hql = "";
		// 如果字段名为空则默认查询所有
		if (fieldname == null || fieldname.equals("") || value == null
				|| value.equals(""))
			hql = "FROM Dept";
		else
			hql = "FROM Dept dept  where " + fieldname + " like '%" + value
					+ "%'";
		@SuppressWarnings("unchecked")
		List<Dept> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

	/*
	 * 获取最大id值
	 * 
	 * @see com.arch.dao.QueryDAO#getMaxID()
	 */
	@Override
	public int getMaxID() {
		String sql = "select max(deptid)+1 from Dept";
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

	/*
	 * 根据条件查询到部门列表，如果字段名和值为空则默认查询所有
	 * 
	 * @see com.arch.dao.QueryDAO#getDatas(java.lang.String, java.lang.String,
	 * int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		// TODO Auto-generated method stub
		final int pageSize1 = pageSize;
		final int startRow1 = startRow;
		final String queryName = fieldname;
		final String queryValue = value;
		String sql = "";

		if (queryName == null || queryName.equals("") || queryValue == null
				|| queryValue.equals(""))
			sql = "FROM Dept";
		else
			sql = "FROM Dept where " + fieldname + " like '%" + value + "%'";
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

	/*
	 * 根据id查找到相应部门的数据
	 * 
	 * @see com.arch.dao.QueryDAO#queryById(int)
	 */
	@Override
	public Dept queryById(long id) {
		return (Dept) this.getHibernateTemplate().get(Dept.class, id);
	}

}
