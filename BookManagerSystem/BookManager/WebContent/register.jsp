<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<link rel="stylesheet" href="CSS/regis.css" />
<style type="text/css">
.stats1{
		color:#aaa;
	}
	.stats2{
		color:#000;
	}
	.stats3{
		color:red;
	}
	.stats4{
		color:green;
	}
</style>
<script type="text/javascript" src="Script/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="Script/jquery-validate.js"></script>
<script type="text/javascript" src="Script/check.js"></script>
<script type="text/javascript">
$(function(){
	$('#username').blur(function(){
		var username=$(this).val();
		//进行Ajax请求
		$.get('checkName',{'username':username},function(data){
			if(data=='Error'){
				$('#name').remove();
				$('#Error').remove();
				$('#OK').remove();
				$('#username').after('<span id="Error" style="color:red">该用户名已经注册</span>');
			}else if (username.match(/^\S+$/) && username.length >=3 && username.length <= 20){
				$('#name').remove();
				$('#OK').remove();
				$('#Error').remove();
				$('#username').after('<span id="OK" style="color:green">该用户名可以使用</span>');
			}else{
				$('#name').remove();
				$('#OK').remove();
				$('#Error').remove();
				$('#username').after('<span id="OK" style="color:red">用户名长度为3~20位，不能为空格</span>');
			}
		});
	});
	
	$('input[name="verify"]').blur(function(){
		var code=$(this).val();
		//进行Ajax请求
		$.get('checkCode',{'code':code},function(data){
			if(data=='codeError'){
				$('#codeStatus').remove();
				$('#codeError').remove();
				$('#codeOK').remove();
				$('#verify-img').after('<span id="codeError" style="color:red">验证码错误</span>');
			}else{
				$('#codeStatus').remove();
				$('#codeOK').remove();
				$('#codeError').remove();
				$('#verify-img').after('<span id="codeOK" style="color:green">输入正确</span>');
			}
		});
	});
});
</script>		
</head>
<body>
	<div id='logo'></div>
	<div id='reg-wrap'>
		<form action="registerAction" method="post" name="register" onsubmit="return formCheck('click')">
			<fieldset>
				<legend>用户注册</legend>
				<p>
					<label for="username">用户名：</label>
					<input type="text" name="user.username" id="username" class='input'><span class="stats1" id="name">用户名长度为3~20位，不能为空格</span>
				</p>
				<p>
					<label for="password">密码：</label>
					<input type="password" name="user.password" id="password" class='input'><span class="stats1">密码长度为6~20位，不能为空格</span>
				</p>
				<p>
					<label for="repass">确认密码：</label>
					<input type="password" name="repass" id="repass" class='input'><span class="stats1">请确认密码，须与上面密码保持一致</span>
				</p>
				<p>
					<label for="realname">真实姓名：</label>
					<input type="text" name="user.realname" id="realname" class='input'><span class="stats1">请填写您的真实姓名</span>
				</p>
				<p>
					<label for="tel">联系电话：</label>
					<input type="text" name="user.tel" id="tel" class='input'><span class="stats1">请填写您的联系电话</span>
				</p>
				<p>
					<label for="verify">验证码：</label>
					<input type="text" name='verify' class='input' id='verify'/>
					<img src="image" width='80' height='25' id='verify-img' onclick="this.src=this.src+'?'+Math.random()"/><span class="stats1" id="codeStatus">请输入正确的验证码</span>
				</p>
				<p class="run">
					<label>
						<a href="login.jsp">返回登录页面</a>
					</label>
						<input type="submit" name="submit" value="注册">
						<input type="reset" name="reset" value="重置">
				</p>
			</fieldset>
		</form>
	</div>
</body>
</html>