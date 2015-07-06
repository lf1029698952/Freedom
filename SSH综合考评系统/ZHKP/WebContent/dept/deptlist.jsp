<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>
	<center>
		<div>
			<table border="1">
				<tr>
					<th>部门编号</th>
					<th>部门名称</th>
					<th>部门类型</th>
					<th>部门级别</th>
					<th>创建时间</th>

				</tr>
				<s:iterator value="availableItems">
					<tr>
						<td><s:property value="deptid" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="type" /></td>
						<td><s:property value="level" /></td>
						<td><s:property value="create_time" /></td>
					</tr>
				</s:iterator>
			</table>

		</div>
		<div id="footer">
			<p>
				共
				<s:property value="totalRows" />
				行&nbsp; 第
				<s:property value="currentPage" />
				页&nbsp; 共
				<s:property value="pager.getTotalPages()" />
				页&nbsp; <a
					href="<s:url value="deptlist.action">
                <s:param name="currentPage" value="currentPage"/>
                <s:param name="pagerMethod" value="'first'"/>
                
            </s:url>">首页</a>
				<a
					href="<s:url value="deptlist.action">
                <s:param name="currentPage" value="currentPage"/>
                <s:param name="pagerMethod" value="'previous'"/>
            </s:url>">上一页</a>
				<a
					href="<s:url value="deptlist.action">
                <s:param name="currentPage" value="currentPage"/>
                <s:param name="pagerMethod" value="'next'"/>
            </s:url>">下一页</a>
				<a
					href="<s:url value="deptlist.action">
                <s:param name="currentPage" value="currentPage"/>
                <s:param name="pagerMethod" value="'last'"/>
            </s:url>">尾页</a>
			</p>
		</div>
		<a href="javascript:history.go(-1);">这里返回</a>
	</center>
</body>
</html>