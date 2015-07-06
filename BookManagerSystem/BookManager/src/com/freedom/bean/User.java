package com.freedom.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="USERS")
public class User implements Serializable{
	@Id
	private String username;
	private String password;
	private String realname;
	private String tel;
	private int level;

	public User() {
		super();
	}

	public User(String username, String password, String realname, String tel,
			int level) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.tel = tel;
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
