package cn.edu.swu.zhkp.service;

import cn.edu.swu.zhkp.dao.impl.RoleDao;
import cn.edu.swu.zhkp.entity.Role;

public class RoleServer {
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Role getRoleById(long id) {
		return roleDao.queryById(id);
	}
}
