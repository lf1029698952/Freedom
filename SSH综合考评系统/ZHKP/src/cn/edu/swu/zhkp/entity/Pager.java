package cn.edu.swu.zhkp.entity;

/**
 * 分页实体（按Tab键可以查看详细用法）
 * @author Medici.Yan
 * @see	
 * 调用方法：<br/>
 * 1.在相应的Action中创建一个私有属性： private Pager pager;<br/>
 * 2.在相应的Action中创建一个私有属性private PagerDAO pagerdao;<br/>
 * 3.建立相应的属性  <br/>
 *   protected Collection availableItems;//集合用于存放取出的数据<br/>
 *   protected String currentPage;//当前页<br/>
 *   protected String pagerMethod;//分页的方式<br/>
 *   protected String totalRows;//总页数<br/>
 *   protected String queryName;//接收查询的字段名<br/>
 *   protected String queryValue;//接收查询的值<br/>
 *   protected String queryMap;//用于存放下一次queryName和queryValue的Map,用~分割<br/>
 *   <br/>
 *   <b>举例：</b><br/>
 *   <br/>
 *   public String list() throws Exception{<br/>
    	if(queryMap==null || queryMap.equals("")){<br/>
    		//如果为空<br/>
    	}else{<br/>
    		String [] str = queryMap.split("~");<br/>
    		this.setQueryName(str[0]);<br/>
    		this.setQueryValue(str[1]);<br/>
    	}<br/>
    	//得到总行数<br/>
    	int totalRow=deptdao.getRows(this.getQueryName(),this.getQueryValue());<br/>
        //获得分页信息<br/>
    	pager=pagerdao.getPager(this.getCurrentPage(), this.getPagerMethod(), totalRow);<br/>
        //设置当前页<br/>
        this.setCurrentPage(String.valueOf(pager.getCurrentPage()));<br/>
        //设置总行数<br/>
        this.setTotalRows(String.valueOf(totalRow));<br/>
        //得到结果集<br/>
        availableItems=deptdao.getDatas(this.getQueryName(),this.getQueryValue(),pager.getPageSize(), pager.getStartRow());<br/>
		//设置查询条件<br/>
        this.setQueryName(this.getQueryName());<br/>
        this.setQueryValue(this.getQueryValue());<br/>
    	return "list";//返回<br/>
    }<br/>
 */
public class Pager {
	private int totalRows; //总行数
	private int pageSize = 2; //每页显示的行数
	private int currentPage; //当前页号
	private int totalPages; //总页数
	private int startRow; //当前页在数据库中的起始行
	public Pager() {
	}
	public Pager(int _totalRows) {
		totalRows = _totalRows;
		totalPages = totalRows/pageSize;
		int mod = totalRows%pageSize;
		if(mod>0){
			totalPages++;
		}
		currentPage=1;
		startRow = 0;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * 第一页
	 */
	public void first(){
		currentPage =1;
		startRow =0;
	}
	/**
	 * 前一页
	 */
	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}
	
	/**
	 *下一页 
	 */
	public void next() {
        if (currentPage < totalPages) {
            currentPage++;
        }
        startRow = (currentPage - 1) * pageSize;
    }
	/**
	 * 尾页
	 */
	public void last() {
        currentPage = totalPages;
        startRow = (currentPage - 1) * pageSize;
    }
	/**
	 * 跳转
	 * @param _currentPage
	 */
	public void refresh(int _currentPage) {
        currentPage = _currentPage;
        if (currentPage < 1) {
            first();
        }
        if (currentPage > totalPages) {
            last();
        }
    }
}
