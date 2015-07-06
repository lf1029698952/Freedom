package cn.edu.swu.zhkp.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Dao 基类
 * 
 * @author Medici.Yan
 * @param <T>
 *
 */
public class BaseDAO<T> extends HibernateDaoSupport {
	/**
	 * 存储
	 * 
	 * @param t
	 */
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
}
