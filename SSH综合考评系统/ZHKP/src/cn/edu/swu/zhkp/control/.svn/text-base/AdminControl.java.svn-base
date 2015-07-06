package cn.edu.swu.zhkp.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.edu.swu.zhkp.entity.Admin;
import cn.edu.swu.zhkp.entity.Course;
import cn.edu.swu.zhkp.entity.Gather;
import cn.edu.swu.zhkp.entity.Role;
import cn.edu.swu.zhkp.entity.User;
import cn.edu.swu.zhkp.service.AdminServer;
import cn.edu.swu.zhkp.service.RoleServer;
import cn.edu.swu.zhkp.utility.FileUtility;

public class AdminControl {
	private File upload;
	private String uploadFileName;
	private String path;
	private AdminServer adminServer;
	private RoleServer roleServer;
	private User adduser;
	private User uuser;
	private Gather gather;
	private int pagenum = 1;
	private int pagesize = 10;
	private Admin admin;

	public Gather getGather() {
		return gather;
	}

	public void setGather(Gather gather) {
		this.gather = gather;
	}

	public RoleServer getRoleServer() {
		return roleServer;
	}

	public void setRoleServer(RoleServer roleServer) {
		this.roleServer = roleServer;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public User getUuser() {
		return uuser;
	}

	public void setUuser(User uuser) {
		this.uuser = uuser;
	}

	public User getAdduser() {
		return adduser;
	}

	public void setAdduser(User adduser) {
		this.adduser = adduser;
	}

	public AdminServer getAdminServer() {
		return adminServer;
	}

	public void setAdminServer(AdminServer adminServer) {
		this.adminServer = adminServer;
	}

	public File getUpload() {
		return upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getPath() {
		return ServletActionContext.getServletContext().getRealPath("/")
				+ "upload\\" + this.getUploadFileName();
	}

	/************************** Action ***************************************/

	public String addusers() {
		return "addusers";
	}

	public String usersupload() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		InputStream is;
		try {
			is = new FileInputStream(upload);
			FileUtility.saveUploadFile(is, this.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			session.setAttribute("message", "找不到文件！");
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			session.setAttribute("message", "上传失败！");
			return "error";
		}
		adminServer.readExcelUsers(this.getPath(), session);
		System.out.println(this.getPath());
		return "upload";
	}

	/**
	 * 添加单独的User
	 * 
	 * @return
	 */
	public String adduser() {
		return "adduser";
	}

	public String takeadduser() {
		String str = adminServer.adduser(adduser);
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (str.equals("error")) {
			session.setAttribute("message", "用户已存在");
			return "error";
		}
		session.setAttribute("message", "用户添加成功");
		return "success";
	}

	public String deleteuser() {
		if (ServletActionContext.getRequest().getParameter("userid") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"userid"))) {
			long userid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("userid"));
			String str = adminServer.deleteUser(userid);
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			session.setAttribute("message", str);
		}
		return "deleteok";
	}

	/**
	 * 响应adminlist
	 * 
	 * @return
	 */
	public String list() {
		if (ServletActionContext.getRequest().getParameter("pagenum") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"pagenum"))) {
			pagenum = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pagenum"));
		}
		List<User> users = adminServer.getUsersPage(pagenum, pagesize);
		int allpagenum = (int) Math.ceil((double) adminServer.getUserNum()
				/ (double) this.pagesize);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("users", users);
		request.setAttribute("allpagenum", allpagenum);
		request.setAttribute("currentpage", pagenum);
		return "userslist";
	}

	/**
	 * 
	 */
	public String userdetail() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (ServletActionContext.getRequest().getParameter("userid") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"userid"))) {
			long userid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("userid"));
			User u = adminServer.getUser(userid);

			if (u == null) {
				session.setAttribute("message", "找不到用户！");
				return "error";
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("user", u);
		} else {
			session.setAttribute("message", "没有输入用户id！");
			return "error";
		}
		return "userdetail";
	}

	public String upgradeuser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (request.getParameter("id") != null
				|| !"".equals(request.getParameter("id"))) {
			long userid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("id"));
			User u = adminServer.getUser(userid);
			if (u == null) {
				session.setAttribute("message", "用户不存在！");
				return "error";
			}
			u.setUname(request.getParameter("name"));
			u.setAcademy(request.getParameter("academy"));
			u.setEnrollment(request.getParameter("enrollment"));
			u.setSchoolnum(request.getParameter("schoolnum"));
			u.setMajor(request.getParameter("major"));
			adminServer.uploadUser(u);
		} else {
			session.setAttribute("message", "用户错误！");
			return "error";
		}
		session.setAttribute("message", "更改成功！");
		return "success";
	}

	/************************************** 添加管理员（辅导员） *****************************************/

	public String addteacher() {
		return "addteacher";
	}

	public String takeaddteacher() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getParameter("role") != null
				&& !"".equals(request.getParameter("role"))) {
			Long roleid = Long.parseLong(request.getParameter("role"));
			Role r = roleServer.getRoleById(roleid);
			admin.setRole(r);
		}
		boolean b = adminServer.addTeacher(admin);
		HttpSession session = request.getSession();
		if (!b) {
			session.setAttribute("message", "添加失败，用户名已存在！");
			return "error";
		}
		session.setAttribute("message", "添加成功！");
		return "success";
	}

	/**
	 * 查看所有的老师
	 * 
	 * @return
	 */
	public String teacherlist() {
		if (ServletActionContext.getRequest().getParameter("pagenum") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"pagenum"))) {
			pagenum = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pagenum"));
		}

		List<Admin> admins = adminServer.getAdminsPage(pagenum, pagesize);
		int allpagenum = (int) Math.ceil((double) adminServer.getUserNum()
				/ (double) this.pagesize);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("admins", admins);
		request.setAttribute("allpagenum", allpagenum);
		request.setAttribute("currentpage", pagenum);
		return "teacherlist";
	}

	public String deleteadmin() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (ServletActionContext.getRequest().getParameter("adminid") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"adminid"))) {
			long adminid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("adminid"));
			String str = adminServer.deleteAdmin(adminid);
			session.setAttribute("message", "删除admin" + str);
		} else {
			session.setAttribute("message", "用户传入错误~");
		}
		return "deleteok";
	}

	/************************************* 添加删除课程 **************************************************/
	public String addcourses() {
		return "addcourses";
	}

	/**
	 * 导入，上传课程
	 * 
	 * @return
	 */
	public String coursesupload() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		InputStream is;
		try {
			is = new FileInputStream(upload);
			FileUtility.saveUploadFile(is, this.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			session.setAttribute("message", "找不到文件！");
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			session.setAttribute("message", "上传失败！");
			return "error";
		}
		adminServer.readExcelCourses(this.getPath(), session);
		System.out.println(this.getPath());
		return "upload";
	}

	/**
	 * 查看所有的课程
	 * 
	 * @return
	 */
	public String courselist() {
		if (ServletActionContext.getRequest().getParameter("pagenum") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"pagenum"))) {
			pagenum = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pagenum"));
		}

		List<Course> courses = adminServer.getCoursesPage(pagenum, pagesize);
		int allpagenum = (int) Math.ceil((double) adminServer.getCourseNum()
				/ (double) this.pagesize);
		System.out.println("allpagenum " + allpagenum);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("courses", courses);
		request.setAttribute("allpagenum", allpagenum);
		request.setAttribute("currentpage", pagenum);
		return "courselist";
	}

	public String deletecourse() {
		if (ServletActionContext.getRequest().getParameter("courseid") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"courseid"))) {
			long courseid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("courseid"));
			String str = adminServer.deleteCourse(courseid);
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			session.setAttribute("message", str);
		}
		return "deleteok";
	}

	/************************************* 班级的操作 ************************************************/

	public String creategatherui() {
		return "creategatherui";
	}

	public String creategather() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin curadmin = adminServer.getcurrentAdmin();
		gather.setAdmin(curadmin);
		adminServer.createGather(gather);
		session.setAttribute("message", "创建班级" + gather.getGname() + "成功");
		return "success";
	}

	public String gatherlist() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (ServletActionContext.getRequest().getParameter("pagenum") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"pagenum"))) {
			pagenum = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pagenum"));
		}
		if (session.getAttribute("admin") == null) {
			session.setAttribute("message", "请先登录！");
			return "error";
		}
		Admin curadmin = (Admin) session.getAttribute("admin");
		List<Gather> gathers = adminServer.getGathersPage(pagenum, pagesize,
				curadmin);
		int allpagenum = (int) Math.ceil((double) adminServer
				.getGatherNum(curadmin) / (double) this.pagesize);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("gathers", gathers);
		request.setAttribute("allpagenum", allpagenum);
		request.setAttribute("currentpage", pagenum);
		return "gatherlist";
	}

	public String deletegather() {
		if (ServletActionContext.getRequest().getParameter("gatherid") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"gatherid"))) {
			long gatherid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("gatherid"));
			String str = adminServer.deleteGather(gatherid);
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			session.setAttribute("message", str);
		}
		return "deleteok";
	}

	public String gatherdetail() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (ServletActionContext.getRequest().getParameter("gatherid") != null
				&& !"".equals(ServletActionContext.getRequest().getParameter(
						"gatherid"))) {
			long gatherid = Long.parseLong(ServletActionContext.getRequest()
					.getParameter("gatherid"));
			Gather g = adminServer.getGather(gatherid);

			if (g == null) {
				session.setAttribute("message", "找不到班级！");
				return "error";
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("gather", g);
		} else {
			session.setAttribute("message", "没有输入用户id！");
			return "error";
		}
		return "gatherdetail";
	}

	/*************************************** 登陆 **************************************************/
	public String loginui() {
		return "login";
	}

	/************************************** 检查登陆 *****************************************************/
	public String login() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin getadmin = adminServer.getAdminByUsername(admin.getUsername());
		if (getadmin == null) {
			session.setAttribute("message", "用户名不存在");
			return "error";
		}
		if (!getadmin.getPassword().equals(admin.getPassword())) {
			session.setAttribute("message", "密码错误");
			return "error";
		}
		session.setAttribute("message", "登陆成功");
		session.setAttribute("admin", getadmin);
		return "success";
	}
}