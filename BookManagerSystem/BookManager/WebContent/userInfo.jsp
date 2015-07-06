<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
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
				<h3 style="color:red"><a href="user">所有用户列表</a></h3>
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>用户名</td>
					<td>密码</td>
					<td>真实姓名</td>
					<td>联系方式</td>
					<td>管理员</td>
					<td>修改权限</td>
					<td>编辑用户</td>
					<td>删除</td>
				</tr>
				<c:forEach var="user" items="${list}">
					<tr>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td>${user.realname}</td>
						<td>${user.tel}</td>
						<td>${user.level}</td>
						<td><a href="#">修改</a></td>
						<td><a href="#">编辑</a></td>
						<td><a href="#">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div>
					<a href="user?page=1">第一页</a>&nbsp;&nbsp;|&nbsp;
				<c:if test="${page <= 1}">
					<a href="user?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page > 1}">
					<a href="user?page=${page - 1}">上一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page < totalPage && page > 1}">
					<a href="user?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
					<a href="user?page=${totalPage}">最后一页</a>
			</div>
	</div>
</body>
</html>