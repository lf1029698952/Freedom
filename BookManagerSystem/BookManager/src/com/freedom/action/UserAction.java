package com.freedom.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.freedom.bean.Book;
import com.freedom.bean.User;
import com.freedom.dao.impl.UserDao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	User user;
	UserDao userDao;
	
	private int page = 1;
	private int pageSize = 3;
	private int totalPage = 1;

	public int getPage() {
	    return page;
	}

	public void setPage(int page) {
	    this.page = page;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String login() {

		if (user.getUsername().equals("") || user.getPassword().equals("")) {
			return "null";
		}
		
		boolean flag = userDao.login(user.getUsername(), user.getPassword());
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("username", user.getUsername());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("username", user.getUsername());
		
		if (flag) {
			return "success";
		} else {
			return "error";
		}
	}

	public String register() {
		userDao.register(user.getUsername(), user.getPassword(), user.getRealname(), user.getTel());

		return "success";
	}
	
	public String show() {
		List<User> list = userDao.query(pageSize, page);
		System.out.println(list);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		totalPage = userDao.queryCount(pageSize);
		request.setAttribute("totalPage", totalPage);
		
		return "success";
	}
	
	public String loginOut() {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("username");
		session.invalidate();
		
		return "success";
	}

}
