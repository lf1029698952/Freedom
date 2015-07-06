<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>用户登录</title>
    <link rel="stylesheet" href="CSS/login.css" />
    <link rel="stylesheet" href="Script/JqueryUI/jquery-ui-1.9.2.min.css" />
    <script type="text/javascript" src='Script/jquery-1.7.2.min.js'></script>
    <script type="text/javascript" src='Script/JqueryUI/jquery-ui-1.9.2.min.js'></script>
    <script type="text/javascript" src='Script/login.js'></script>
    <script type="text/javascript">
    	$(function(){
    		$('input[name="code"]').blur(function(){
    			var code=$(this).val();
    			//进行Ajax请求
    			$.get('checkCode',{'code':code},function(data){
    				if(data=='codeError'){
    					$('#codeError').remove();
    					$('#codeOK').remove();
    					$('#imageCode').after('<span id="codeError" style="color:red">验证码错误</span>');
   					  	$("#form").submit(function(e){
   						    e.preventDefault();
   						});

    				}else{
    					$('#codeOK').remove();
    					$('#codeError').remove();
    					$('#imageCode').after('<span id="codeOK" style="color:green">输入正确</span>');
    				}
    			});
    		});
    		
    	});
    </script>
</head>
<body>
	<div id='top'>
        <a href='#' target='_blank'>
            <img src='Images/blogo.png' width='270' height='52'/>
        </a>
        <a href='#' class='home'>-登录首页-</a>
    </div>
	<div id="main">
		<div id="login">
			<p class='user_logo'><b>登录</b></p>
			<div class="login_form">
				<form action="loginAction" method="post" name="login" id="form">
						<p>
							<label>用户名：</label>
							<label><input type="text" name="user.username" class='input-big'/></label>
						</p>
						<p>
							<label>密码：</label>
							<label><input type="password" name="user.password" class='input-big'/></label>
						</p>
						<p>
							<label>验证码：</label>
							<label><input type="text" name="code" class='input-medium'/></label>
							<label><img alt="请输入验证码" src="image" id="imageCode" width="100" height="20" onclick="this.src=this.src+'?'+Math.random()"/></label>
						</p>
						 <p class='login_btn'>
							<label><input type="submit" name="submit" value="" class='loginbg'/><a href="register.jsp">点击注册</a></label>
						</p>
				</form>
			</div>
		</div>
	</div>
	<div id='dialog'></div>
</body>
</html>