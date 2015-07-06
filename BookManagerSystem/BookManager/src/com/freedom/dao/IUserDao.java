package com.freedom.dao;


public interface IUserDao {
	boolean login(String username, String password);
	void register(String username, String password, String realname, String tel);
}
