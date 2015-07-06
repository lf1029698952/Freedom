<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅图书</title>
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
		<form action="borrow" method="post">
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>读者学号：</td>
					<td><input type="text" name="borrow.reader.readerno"></td>
				</tr>
				<tr>
					<td>图书编号：</td>
					<td><input type="text" name="borrow.book.bookno"></td>
				</tr>
				<tr>
					<td>借出时间：</td>
					<td><input type="text" name="borrow.time"></td>
				</tr>
				<tr >
					<td><input type="submit" name="submit" value="借阅"></td>
					<td><input type="reset" name="reset" value="取消"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>