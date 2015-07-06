<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>班级名</th>
			<th>专业</th>
			<th>负责老师</th>
			<th>操作</th>
		</tr>
		<c:forEach var="gather" items="${gathers}">
			<tr>
				<td>${gather.gname}</td>
				<td>${gather.major}</td>
				<td>${gather.admin.realname}</td>
				<td><a href="admindeletegather?gatherid=${gather.id} "> 删除</a></td>
			</tr>

		</c:forEach>

	</table>
	当前页面：
	<s:property value="#attr.currentpage" />
	总共页面：
	<s:property value="#attr.allpagenum" />
	<s:a href="adminlist?pagenum=1">1</s:a>
	<s:a href="adminlist?pagenum=2">2</s:a>
</body>
</html>