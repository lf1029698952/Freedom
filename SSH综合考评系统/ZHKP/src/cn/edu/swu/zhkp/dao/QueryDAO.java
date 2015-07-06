package cn.edu.swu.zhkp.dao;

import java.util.List;

/**
 * QueryDao接口，在用于查询分页
 * @author Medici.Yan
 *
 */
public interface QueryDAO<T> {
	 /**
	  * 根据条件获取符合条件的行数
	 * @param fieldname 字段名，为空则默认查询所有
	 * @param value		值，为空则默认查询所有
	 * @return
	 */
	int getRows(String fieldname,String value);//获得总行数
	 /**
	  * 获取最大id值
	 * @return
	 */
	int getMaxID();//获得最大ID值
	 /**
	  * 根据条件查询
	 * @param fieldname 字段名
	 * @param value		值
	 * @param pageSize	每页数据
	 * @param startRow	开始的行数
	 * @return
	 */
	List<T> getDatas(String fieldname,String value,int pageSize, int startRow);//根据条件查询
	 /**
	  * 根据主键查询相应的数据
	 * @param id	主键值(int)
	 * @return
	 */
	T queryById(long id);//根据主键获得一个数据
}
