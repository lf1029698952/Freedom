package com.freedom.dao;

public interface IBaseDao {
	boolean save();
	boolean delete();
	boolean update();
	boolean query(String username);
}
