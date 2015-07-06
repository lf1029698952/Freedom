<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addReader</title>
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
		<form action="addReaderAction" method="post">
			<table border="1" style="border-collapse: collapse">
				<tr>
					<td>读者学号：</td>
					<td><input type="text" name="reader.readerno"></td>
				</tr>
				<tr>
					<td>读者姓名：</td>
					<td><input type="text" name="reader.readername"></td>
				</tr>
				<tr>
					<td>读者性别：</td>
					<td><input type="text" name="reader.sex"></td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td><input type="text" name="reader.tel"></td>
				</tr>
				<tr>
					<td>读者单位：</td>
					<td><input type="text" name="reader.department"></td>
				</tr>
				<tr>
					<td>
						<input type="submit" name="submit" value="添加">
					</td>
					<td>
						<input type="reset" name="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>