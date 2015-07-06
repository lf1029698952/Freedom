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
import cn.edu.swu.zhkp.entity.Awarded;
import cn.edu.swu.zhkp.entity.Dept;

public class AwardedDAO extends BaseDAO<Awarded> implements QueryDAO<Awarded>{
	private SessionFactory sessionFactory;
	@Override
	public int getRows(String fieldname, String value) {
		// TODO Auto-generated method stub
		String hql="";
		//如果字段名为空则默认查询所有
        if(fieldname==null||fieldname.equals("")||value==null||value.equals(""))
            hql="select count(*) FROM Awarded awarded ";
        else    
            hql="select count(*) FROM Awarded awarded  where awarded."+fieldname+" like '%"+value+"%'";
        @SuppressWarnings("unchecked")
        //HQL用法
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query q =  session.createQuery(hql);
        long count = (long) q.uniqueResult();
        return (int)count;
        //template用法
         //List<Integer> list = this.getHibernateTemplate().find(hql);
        //return list.get(0);
	}

	@Override
	public int getMaxID() {
		String sql = "select max(ID)+1 from Awarded";
		List list1 = (List)this.getHibernateTemplate().find(sql);
		Iterator it = list1.iterator();
		int maxid=1;
		if(it.hasNext()){
			Object o = it.next();
			if(o!=null){
				maxid=(int)o;
			}
		}
		return maxid;
	}

	@Override
	public List<Awarded> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		final int pageSize1=pageSize;
        final int startRow1=startRow;
        final String queryName=fieldname;
        final String queryValue=value;
        String sql="";
        
        if(queryName==null||queryName.equals("")||queryValue==null||queryValue.equals(""))
            sql="FROM Awarded";
        else    
            sql="FROM Awarded where "+fieldname+" like '%"+value+"%'";
        final String sql1=sql;
        
        return this.getHibernateTemplate().executeFind(new HibernateCallback(){
            public List doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(sql1);
                query.setFirstResult(startRow1);
                query.setMaxResults(pageSize1);
                return query.list();
            }
        });

	
	}

	@Override
	public Awarded queryById(long id) {
		return (Awarded)this.getHibernateTemplate().get(Awarded.class, id);
	}
	@Override
	public void save(Awarded awarded){
		this.getHibernateTemplate().save(awarded);
	}
	@Override
	public void delete(Awarded awarded){
		this.getHibernateTemplate().delete(awarded);
	}
	@Override
	public void update(Awarded awarded){
		this.getHibernateTemplate().update(awarded);
	}
	
	public List<Awarded> getAllAwardeds(String fieldname,String value){
        final String queryName=fieldname;
        final String queryValue=value;
        String sql="";
        if(queryName==null||queryName.equals("")||queryValue==null||queryValue.equals(""))
            sql="FROM Awarded";
        else    
            sql="FROM Awarded awarded where awarded."+fieldname+"="+value+"'";
        final String sql1=sql;
        return this.getHibernateTemplate().find(sql1);
	}


}
