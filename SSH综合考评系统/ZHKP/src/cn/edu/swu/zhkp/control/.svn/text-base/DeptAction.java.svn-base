package cn.edu.swu.zhkp.control;

import java.util.Collection;

import cn.edu.swu.zhkp.dao.PagerDAO;
import cn.edu.swu.zhkp.dao.impl.DeptDAO;
import cn.edu.swu.zhkp.entity.Dept;
import cn.edu.swu.zhkp.entity.Pager;

public class DeptAction {
	private Dept dept;
	private DeptDAO deptdao;
	private Pager pager;
	private PagerDAO pagerdao;

	protected String deptid;// 部门编号
	protected Collection availableItems;// 存放结果集
	protected String currentPage;// 当前页号
	protected String pagerMethod;// 分页的方式
	protected String totalRows;// 总行数

	protected String queryName;// 查询的字段名
	protected String queryValue;// 查询的值
	protected String queryMap;

	// 对应的Action
	/**
	 * 列出所有部门
	 * 
	 * @return
	 */
	public String list() {
		if (queryMap == null || queryMap.equals("")) {
		} else {
			String[] str = queryMap.split("~");
			this.setQueryName(str[0]);
			this.setQueryValue(str[1]);
		}
		// 得到总行数
		int totalRow = deptdao.getRows(this.getQueryName(),
				this.getQueryValue());
		// 获得分页信息
		pager = pagerdao.getPager(this.getCurrentPage(), this.getPagerMethod(),
				totalRow);
		// 设置当前页
		this.setCurrentPage(String.valueOf(pager.getCurrentPage()));
		// 设置总行数
		this.setTotalRows(String.valueOf(totalRow));
		// 得到结果集
		availableItems = deptdao.getDatas(this.getQueryName(),
				this.getQueryValue(), pager.getPageSize(), pager.getStartRow());

		this.setQueryName(this.getQueryName());
		this.setQueryValue(this.getQueryValue());
		return "list";
	}

	/**
	 * 添加和修改 添加的话id为传来的id是0，修改的话id就是对应的id号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		int id;
		if (dept.getDeptid() == 0) {
			id = deptdao.getMaxID();
		} else {
			id = this.getDept().getDeptid();
		}
		Dept dept1 = deptdao.queryById(id);
		if (dept1 == null)
			deptdao.save(this.getDept());
		else
			deptdao.update(this.getDept());

		this.setQueryName(this.getQueryName());
		this.setQueryValue(this.getQueryValue());

		if (this.getQueryName() == null || this.getQueryValue() == null
				|| this.getQueryName().equals("")
				|| this.getQueryValue().equals("")) {

		} else {
			queryMap = this.getQueryName() + "~" + this.getQueryValue();
		}

		return "success";
	}

	// get set 方法
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public DeptDAO getDeptdao() {
		return deptdao;
	}

	public void setDeptdao(DeptDAO deptdao) {
		this.deptdao = deptdao;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public PagerDAO getPagerdao() {
		return pagerdao;
	}

	public void setPagerdao(PagerDAO pagerdao) {
		this.pagerdao = pagerdao;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Collection getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(Collection availableItems) {
		this.availableItems = availableItems;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPagerMethod() {
		return pagerMethod;
	}

	public void setPagerMethod(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}

	public String getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public String getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(String queryMap) {
		this.queryMap = queryMap;
	}

}
