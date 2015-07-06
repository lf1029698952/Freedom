<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.box {
	background-color: #EDF3DE;
	height: 200px;
	width: 300px;
	margin-top: -100px;
	margin-left: -150px;
	position: absolute;
	left: 50%;
	top: 40%;
}
.img_box{height: 32px;width:32px;margin-top: -16px;margin-left: -32px;position: absolute;left:25%;top:50%}
.word_box{height: 44px;width:150px;margin-top: -44px;margin-left: 0px;position: absolute;left:45%;top:40%}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function goback() {
		window.history.go(-1);
	}
	setTimeout("goback()", 3000);
</script>
<title>操作失败</title>
</head>
<body>
	<div class="box">
		<div class="img_box"><img alt="Error" src="../img/icon_error.png" /></div>
		<div class="word_box"><h4>操作失败</h4><p>3秒后返回，如果不能返回请点击<a href="javascript:history.go(-1);">这里</a></p></div>
	</div>
</body>
</html>