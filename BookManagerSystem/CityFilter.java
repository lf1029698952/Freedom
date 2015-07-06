package com.cheng2system.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheng2system.pojos.User;

/**
 * 
 * 
 * @author Zhang Bin 
 * 功能：过滤器
 * 
 */
public class CityFilter extends HttpServlet implements Filter {
	// private String encoding;
	private FilterConfig config;
	
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session=request.getSession(true);
		
		String uri = request.getRequestURI();
		if(uri.endsWith("login.do")||uri.endsWith("index.do")){
			chain.doFilter(request, response);
		}else{
			//String encoding = config.getInitParameter("encoding");
			//设置request编码用的字符集
			//request.setCharacterEncoding(encoding);
			User myUser=(User)session.getAttribute("user");
			if(myUser==null){ 
				response.getWriter().print("<script>top.location='" + request.getContextPath() + "/login.do';</script>");
			}else {
				chain.doFilter(request, response);
			}
		}
		
		
		
//		
//		User myUser=(User)session.getAttribute("user");
//		String myUrl=request.getRequestURI();
//		if(myUser==null||myUser.equals(null)){
//			System.out.println("MyLoginFilter的if语句进来了！");
//			request.getRequestDispatcher("login.do").forward(request, response);
//			if(myUrl!=null&&myUrl.equals("")&&(myUrl.indexOf("Login")<0&&myUrl.indexOf("login")<0&&myUrl.indexOf("index")<0)){
//				response.sendRedirect("login.do");
//				return;
//			}
//			
//		}
//		chain.doFilter(request,response);
//		return;
//		
		
		
//		
//		String uri = request.getRequestURI();
//		if (uri.endsWith(".jpg") || uri.endsWith("showUserAction!showUser.do")
//				|| uri.endsWith(".html") || uri.endsWith(".jsp")
//				|| uri.endsWith(".png") || uri.endsWith(".gif")
//				|| uri.endsWith(".js") || uri.endsWith("text!login.do")
//				|| uri.endsWith(".json") || uri.endsWith(".css")
//				|| uri.endsWith(".properties") || uri.endsWith(".xml")
//				|| uri.endsWith("getMyTreeDataAction.do")||uri.endsWith("testAction!getRole.do")
//				|| uri.endsWith("index.jsp") || uri.equals("/city2system/")) {
//			chain.doFilter(req, response);
//			return;
//		}
//		
		
		
//		if(request.getSession().getAttribute("user")== null){
//	    	System.out.println("MyLoginFilter的if语句进来了！");
//	    	request.getRequestDispatcher("login.do").forward(request, response);
//	    	//response.sendRedirect("http://"+request.getHeader("Host")+"/manageinf/login.jsp");
//	    	//chain.doFilter(request,response);
//	    	return;
//
//		}else 
//		{
//			chain.doFilter(request,response);
//		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig; 
		// 这个是拿到web.xml配置的处理中文乱码的构建
		// encoding=filterConfig.getInitParameter("encoding");
	}
	public void destroy() {
		this.config = null; 
	}
}
