<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>读者列表</title>
<style type="text/css">
	body {
		background: url(Images/regis_bg.png) repeat-x #C8E1F0;
	}
	#div{
		position: absolute;
		top: 30%;
		left: 40%
	}
</style>
</head>
<body>
	<div class="div" align="center">
				<h3 style="color:red"><a href="reader">所有读者列表</a></h3>
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>读者编号</td>
					<td>读者姓名</td>
					<td>读者性别</td>
					<td>借书数量</td>
					<td>电话</td>
					<td>单位</td>
					<td>编辑读者</td>
					<td>删除</td>
				</tr>
				<c:forEach var="reader" items="${list}">
					<tr>
						<td>${reader.readerno}</td>
						<td>${reader.readername}</td>
						<td>${reader.sex}</td>
						<td>0</td>
						<td>${reader.tel}</td>
						<td>${reader.department}</td>
						<td><a href="#">编辑</a></td>
						<td><a href="#">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<a href="reader?page=1">第一页</a>&nbsp;&nbsp;|&nbsp;
				<c:if test="${page <= 1}">
					<a href="reader?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page > 1}">
					<a href="reader?page=${page - 1}">上一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page <= totalPage && page > 1}">
					<a href="reader?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
					<a href="reader?page=${totalPage}">最后一页</a>
			</div>
	</div>
</body>
</html>