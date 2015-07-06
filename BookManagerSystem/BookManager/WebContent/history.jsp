<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史记录</title>
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
				<h3 style="color:red"><a href="history">历史记录</a></h3>
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>图书类型</td>
					<td>书名</td>
					<td>作者</td>
					<td>出版社</td>
					<td>ISBN编号</td>
					<td>定价</td>
					<td>借书人</td>
					<td>借出时间</td>
					<td>归还时间</td>
				</tr>
				<c:forEach var="borrow" items="${history}">
					<tr>
						<td>${borrow.book.type}</td>
						<td>${borrow.book.name}</td>
						<td>${borrow.book.author}</td>
						<td>${borrow.book.press}</td>
						<td>${borrow.book.isbn}</td>
						<td>${borrow.book.price}</td>
						<td>${borrow.reader.readerno}</td>
						<td>${borrow.time}</td>
						<td>${borrow.rtime}</td>
					</tr>
				</c:forEach>
			</table>
			<div>
					<a href="history?page=1">第一页</a>&nbsp;&nbsp;|&nbsp;
				<c:if test="${page <= 1}">
					<a href="history?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page > 1}">
					<a href="history?page=${page - 1}">上一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
				<c:if test="${page < totalPage && page > 1}">
					<a href="history?page=${page + 1}">下一页</a>&nbsp;&nbsp;|&nbsp;
				</c:if>
					<a href="history?page=${totalPage}">最后一页</a>
			</div>
	</div>
</body>
</html>