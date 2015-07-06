package com.freedom.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.freedom.dao.impl.BaseDao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CheckAction extends ActionSupport {

	BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void checkName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		boolean flag = baseDao.query(username);
		
			try {
				if (flag) {
					response.getWriter().print("OK");
				}else {
					response.getWriter().print("Error");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void checkCode() throws IOException {
	    
	    HttpServletRequest request = ServletActionContext.getRequest();
	    HttpServletResponse response = ServletActionContext.getResponse();
	    HttpSession session = request.getSession();
	    String validateCode = request.getParameter("code");
	    String code = (String) session.getAttribute("code");
	    
	    if (validateCode.equalsIgnoreCase(code)) {
		response.getWriter().print("codeOK");
	    } else {
		response.getWriter().print("codeError");
	    }
	}
}
