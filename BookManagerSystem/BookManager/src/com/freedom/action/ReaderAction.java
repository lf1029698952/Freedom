package com.freedom.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.freedom.bean.Reader;
import com.freedom.dao.impl.ReaderDao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReaderAction extends ActionSupport{
    
    Reader reader;
    ReaderDao readerDao;
    
    private int page = 1;
    private int pageSize = 3;
    private int totalPage = 1;
    
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public ReaderDao getReaderDao() {
        return readerDao;
    }

    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    public String show() {
	List<Reader> list = readerDao.query(pageSize, page);
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("list", list);
	request.setAttribute("page", page);
	totalPage = readerDao.queryCount(pageSize);
	request.setAttribute("totalPage", totalPage);
	
	return "success";
    }

    public String add() {
	System.out.println(reader.getReaderno());
	Reader newreader = new Reader(reader.getReaderno(), reader.getReadername(), reader.getSex(), reader.getTel(), reader.getDepartment());
	
	readerDao.add(newreader);
	
	return "success";
    }
}
