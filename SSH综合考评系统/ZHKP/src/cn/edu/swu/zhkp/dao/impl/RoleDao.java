package cn.edu.swu.zhkp.dao.impl;

import java.util.List;

import cn.edu.swu.zhkp.dao.BaseDAO;
import cn.edu.swu.zhkp.dao.QueryDAO;
import cn.edu.swu.zhkp.entity.Role;

public class RoleDao extends BaseDAO<Role> implements QueryDAO<Role> {

	@Override
	public int getRows(String fieldname, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Role> getDatas(String fieldname, String value, int pageSize,
			int startRow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role queryById(long id) {
		return this.getHibernateTemplate().get(Role.class, id);
	}

}
