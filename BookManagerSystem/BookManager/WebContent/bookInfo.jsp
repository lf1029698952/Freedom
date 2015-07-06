<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书列表</title>
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
<script type="text/javascript">
	function confirmDelete(val)
	{
		return window.confirm("您确定删除bookno="+val+"的图书吗？");
	}
</script>
</head>
<body>
	<div class="div" align="center">
				<h3 style="color:red"><a href="page">所有图书列表</a></h3>
				<span>
					按时间：<input type="text" name="timeStart">
					至<input type="text" name="timeEnd"><br>
					出版社：<input type="text" name="press">
					书名：<input type="text" name="name">
					作者：<input type="text" name="author">
					<input type="button" name="query" value="查询">
				</span>
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>书名</td>
					<td>作者</td>
					<td>出版社</td>
					<td>ISBN编号</td>
					<td>定价</td>
					<td>数量</td>
					<td>已借出</td>
					<td>添加时间</td>
					<td>备注</td>
					<td>编辑图书信息</td>
					<td>删除</td>
				</tr>
				<c:forEach var="book" items="${list}">
					<tr>
						<td>${book.name}</td>
						<td>${book.author}</td>
						<td>${book.press}</td>
						<td>${book.isbn}</td>
						<td>${book.price}</td>
						<td>${book.number}</td>
						<td>0</td>
						<td>${book.time}</td>
						<td>${book.remark}</td>
						<td><a href="getBookInfo?bookno=${book.bookno}">编辑</a></td>
						<td><a href="deleteBook?bookno=${book.bookno}" onclick='return confirmDelete(${book.bookno})'>删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div>
					<a href="page?page=1">第一页</a>&nbsp;&nbsp;|&nbsp;
				<c:if test="${page <= 1}">
					<a href="page?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page > 1}">
					<a href="page?page=${page - 1}">上一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page < totalPage && page > 1}">
					<a href="page?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
					<a href="page?page=${totalPage}">最后一页</a>
			</div>
	</div>
</body>
</html>