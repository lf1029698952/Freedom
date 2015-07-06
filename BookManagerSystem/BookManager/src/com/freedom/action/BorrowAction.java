package com.freedom.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.freedom.bean.Borrow;
import com.freedom.dao.impl.BorrowDao;

public class BorrowAction {

    Borrow borrow;
    BorrowDao borrowDao;

    public Borrow getBorrow() {
	return borrow;
    }

    public void setBorrow(Borrow borrow) {
	this.borrow = borrow;
    }

    public BorrowDao getBorrowDao() {
	return borrowDao;
    }

    public void setBorrowDao(BorrowDao borrowDao) {
	this.borrowDao = borrowDao;
    }

    public String borrow() throws Exception {

	boolean flag = borrowDao.borrow(borrow, borrow.getBook().getBookno());

	if (flag == false) {
	    return "error";
	} else {
	    return "success";
	}
    }

    public String returnBook() throws Exception {

	boolean flag = borrowDao.returnBook(borrow, borrow.getBook()
		.getBookno(), borrow.getReader().getReaderno());

	if (flag == false) {
	    return "error";
	} else {
	    return "success";
	}
    }

    public String borrowed() throws Exception {

	List list = borrowDao.borrowed();
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("borrow", list);

	return "success";
    }

    public String history() throws Exception {

	List list = borrowDao.history();
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("history", list);

	return "success";
    }

}
