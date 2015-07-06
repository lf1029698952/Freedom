<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改图书</title>
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
		<form action="updateBookAction" method="post">
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>图书类型：</td>
					<td><input type="text" name="book.type" value="${book.type}"></td>
				</tr>
				<tr>
					<td>图书书名：</td>
					<td><input type="text" name="book.name" value="${book.name}"></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" name="book.author" value="${book.author}"></td>
				</tr>
				<tr>
					<td>出版社：</td>
					<td><input type="text" name="book.press" value="${book.press}"></td>
				</tr>
				<tr>
					<td>I-S-B-N编号：</td>
					<td><input type="text" name="book.isbn" value="${book.isbn}"></td>
				</tr>
				<tr>
					<td>定价：</td>
					<td><input type="text" name="book.price" value="${book.price}"></td>
				</tr>
				<tr>
					<td>数量：</td>
					<td><input type="text" name="book.number" value="${book.number}"></td>
				</tr>
				<tr>
					<td>添加时间：</td>
					<td><input type="text" name="book.time" value="${book.time}"></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><textarea rows="5" cols="20" name="book.remark"></textarea></td>
				</tr>
				<tr>
				</tr>
				<tr >
					<td><input type="submit" name="submit" value="修改"></td>
					<td><input type="reset" name="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>