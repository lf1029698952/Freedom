package cn.edu.swu.zhkp.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import cn.edu.swu.zhkp.dao.impl.AdminDao;
import cn.edu.swu.zhkp.dao.impl.CourseDao;
import cn.edu.swu.zhkp.dao.impl.GatherDao;
import cn.edu.swu.zhkp.dao.impl.UserDao;
import cn.edu.swu.zhkp.entity.Admin;
import cn.edu.swu.zhkp.entity.Course;
import cn.edu.swu.zhkp.entity.Gather;
import cn.edu.swu.zhkp.entity.User;

public class AdminServer {
	private UserDao userDao;
	private AdminDao adminDao;
	private CourseDao courseDao;
	private GatherDao gatherDao;

	public GatherDao getGatherDao() {
		return gatherDao;
	}

	public void setGatherDao(GatherDao gatherDao) {
		this.gatherDao = gatherDao;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * 掺入一大堆用户
	 * 
	 * @param users
	 * @param str
	 */
	public String addUsers(Set<User> users) {
		String str = "";
		try {
			for (User user : users) {
				userDao.save(user);
			}
		} catch (Exception e) {
			str = new String("操作失败！");
		}
		str = new String("操作成功！");
		return str;
	}

	public String readExcelUsers(String path, HttpSession session) {

		Set<User> users = new HashSet<User>();
		InputStream input = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			input = new FileInputStream(path);
			fs = new POIFSFileSystem(input);
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				String userinfo[] = new String[5];
				int ci = 0;
				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();
					String cellValue = "";
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						// System.out.print((int)cell.getNumericCellValue()+"[i"+ci+"]---");
						cellValue = String.valueOf((int) cell
								.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING:
						// System.out.print(cell.getStringCellValue()+"[s"+ci+"]---");
						cellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						// System.out.print(cell.getBooleanCellValue()+"[b"+ci+"]---");
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						// System.out.print(cell.getCellFormula()+"[f"+ci+"]---");
						cellValue = cell.getCellFormula();
						break;
					default:
						cellValue = "";
						break;
					}
					userinfo[ci] = cellValue;
					ci++;
				}
				User user = new User();
				for (int i = 0; i < ci; i++) {
					user.setPassword("000000");
					user.setSchoolnum(userinfo[1]);
					user.setUname(userinfo[0]);
					user.setAcademy(userinfo[2]);
					user.setEnrollment(userinfo[3]);
					user.setMajor(userinfo[4]);
				}
				users.add(user);
			}
			System.out.println(users.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			session.setAttribute("message", "上传失败,找不到文件！");
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					session.setAttribute("message", "文件关闭异常！");
					return "error";
				}
			}
		}
		String status;
		status = this.addUsers(users);
		session.setAttribute("message", status);
		return "导入成功";
	}

	/**
	 * 添加单个用户
	 * 
	 * @return
	 */
	public String adduser(User u) {
		if (userDao.getUserBySchoolNum(u.getSchoolnum()) != null) {
			return "error";
		}
		userDao.save(u);
		return "success";
	}

	public String deleteUser(long id) {
		User user = userDao.queryById(id);
		if (user == null) {
			return "error";
		}
		userDao.delete(user);
		return "success";
	}

	/**
	 * 得到页面的list
	 * 
	 * @param pagenum
	 *            处于的页数,从1开始
	 * @param pagesize
	 *            页面的数目
	 * @return
	 */
	public List<User> getUsersPage(int pagenum, int pagesize) {
		return userDao.getDatas(null, null, pagesize, (pagenum - 1) * pagesize);
	}

	/**
	 * 获取user的总个数
	 * 
	 * @return
	 */
	public int getUserNum() {
		int allpagenum = userDao.getRows(null, null);
		return allpagenum;
	}

	public User getUser(long id) {
		User u = userDao.queryById(id);
		return u;
	}

	public void uploadUser(User u) {
		userDao.update(u);
	}

	/********************************* 管理员的管理 **************************************************/
	public boolean addTeacher(Admin admin) {
		if (adminDao.getByUsername(admin.getUsername()) == null) {
			adminDao.save(admin);
			return true;
		}
		return false;
	}

	/**
	 * 获取管理
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	public List<Admin> getAdminsPage(int pagenum, int pagesize) {
		return adminDao
				.getDatas(null, null, pagesize, (pagenum - 1) * pagesize);
	}

	public String deleteAdmin(long adminid) {
		Admin admin = adminDao.queryById(adminid);
		if (admin == null) {
			return "error";
		}
		adminDao.delete(admin);
		return "success";
	}

	/********************************* 上传课程，或者写入课程 ******************************************************/
	public String readExcelCourses(String path, HttpSession session) {
		Set<Course> courses = new HashSet<Course>();
		InputStream input = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			input = new FileInputStream(path);
			fs = new POIFSFileSystem(input);
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				Iterator<Cell> cells = row.cellIterator();
				String courseinfo[] = new String[2];
				int ci = 0;
				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();
					String cellValue = "";
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						// System.out.print((int)cell.getNumericCellValue()+"[i"+ci+"]---");
						cellValue = String.valueOf((int) cell
								.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING:
						// System.out.print(cell.getStringCellValue()+"[s"+ci+"]---");
						cellValue = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						// System.out.print(cell.getBooleanCellValue()+"[b"+ci+"]---");
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_FORMULA:
						// System.out.print(cell.getCellFormula()+"[f"+ci+"]---");
						cellValue = cell.getCellFormula();
						break;
					default:
						cellValue = "";
						break;
					}
					courseinfo[ci] = cellValue;
					ci++;
				}
				Course course = new Course();
				for (int i = 0; i < ci; i++) {
					course.setCourseName(courseinfo[0]);
					course.setCoursepoint(Double.parseDouble(courseinfo[1]));
				}
				courses.add(course);
			}
			System.out.println(courses.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			session.setAttribute("message", "上传失败,找不到文件！");
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					session.setAttribute("message", "文件关闭异常！");
					return "error";
				}
			}
		}
		String status;
		status = this.addCourses(courses);
		session.setAttribute("message", "导入成绩" + status);
		return "导入成功";
	}

	public String addCourses(Set<Course> courses) {
		String str = "";
		try {
			for (Course course : courses) {
				courseDao.save(course);
			}
		} catch (Exception e) {
			str = new String("操作失败！");
		}
		str = new String("操作成功！");
		return str;
	}

	public List<Course> getCoursesPage(int pagenum, int pagesize) {
		return courseDao.getDatas(null, null, pagesize, (pagenum - 1)
				* pagesize);
	}

	public double getCourseNum() {
		return courseDao.getRows(null, null);
	}

	public String deleteCourse(long courseid) {
		Course course = courseDao.queryById(courseid);
		if (course == null) {
			return "error";
		}
		courseDao.delete(course);
		return "success";
	}

	/************************************* 检查session **************************************************/
	public Admin getcurrentAdmin() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Admin cur = (Admin) session.getAttribute("admin");
		return cur;
	}

	public Admin getAdminByUsername(String username) {
		return adminDao.getByUsername(username);
	}

	/**
	 * 创建写入新班级
	 * 
	 * @param gather
	 * @return
	 */
	public boolean createGather(Gather gather) {
		gatherDao.save(gather);
		return true;
	}

	public List<Gather> getGathersPage(int pagenum, int pagesize) {
		return gatherDao.getDatas(null, null, pagesize, (pagenum - 1)
				* pagesize);
	}

	public List<Gather> getGathersPage(int pagenum, int pagesize, Admin admin) {
		return gatherDao.getDatasByAdmin(pagesize, (pagenum - 1) * pagesize,
				admin);
	}

	public int getGatherNum(Admin admin) {
		return gatherDao.getRows(admin);
	}

	/**
	 * 删除
	 * 
	 * @param gatherid
	 * @return
	 */
	public String deleteGather(long gatherid) {
		Gather gather = gatherDao.queryById(gatherid);
		if (gather == null) {
			return "error";
		}
		gatherDao.delete(gather);
		return "success";
	}

	public Gather getGather(long gatherid) {
		Gather gather = gatherDao.queryById(gatherid);
		return gather;
	}
}