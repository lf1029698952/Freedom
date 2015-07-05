<?php if (!defined('THINK_PATH')) exit();?>﻿<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="Keywords" content="找呀找朋友，大学生线下活动社交平台" />
    <meta name="Description" content="找呀找朋友，大学生线下活动社交平台，运动、学习、音乐、旅行、技能、助人为乐、聚会、老乡会、情感、影视/戏剧、动漫/游戏、生活/兴趣。发布活动，找到志同道合的小伙伴一起参与吧" />
	<link rel="shortcut icon" href="__PUBLIC__/Them/default/images/icon.png">
	<link href="__PUBLIC__/Them/default/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="__PUBLIC__/js/jquery-1.10.2.min.js"></script>
    <script src="__PUBLIC__/js/bootstrap.min.js"></script>
    <script src="__PUBLIC__/js/common.js"></script>
    <title>找呀找朋友</title>
  </head>

<body>

<div class="container" style="margin-top:70px;">
	<div class="row">
		<div class="col-md-4 col-md-offset-4 text-center">
			<form class="form-signin" role="form" method="post" action="<?php echo U('runRegister');?>">
				<h2 class="form-signin-heading text-primary"><a href="<?php echo U('Index/index');?>">登录 <a href="<?php echo U('register');?>">注册</a></h2>
				<p><input type="text" name="account" class="form-control" placeholder="用户名" required autofocus/></p>
				<p><input type='radio' name='sex' value='1' checked='checked'/><span class='text-primary'>男</span> 　　<input type='radio' name='sex' value='0'/><span style="color:#ff00ff">女</span></p>
				<p><input type="password" name="password" class="form-control" placeholder="密码" required/></p>
				<p><input type="password" name="repassword" class="form-control" placeholder="确认密码" required/></p>
				<p><input type="text" name="username" class="form-control" placeholder="昵称" required/></p>
				<p>
					<div class='row'>
						<div class='col-md-6'>
							<input type="text" name="verify" class="form-control" placeholder="验证码" required>
						</div>
						<div class='col-md-6'>
							<img src="<?php echo U('Login/verify');?>" id='verify' width="165" height="34"/>
						</div>
					</div>
				</p>
				<div>
				<p><button class="btn btn-lg btn-primary btn-block" type="submit">注册</button></p>
			</form>
		</div>
	</div>
</div>


	<!--底部开始-->
	
	<div id="footer" style='margin-top:100px;'>
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1 text-center text-muted">
					<p>版权信息：@<a href="http://www.springs.pub" class="text-muted" target="_blank">李峰</a> 西南大学 2012级计算机科学与技术学院</p>
					<p>QQ:1029698952　　　2014/1/11-2014/4/8</p>
				</div>
			</div>
		</div>
    </div>
	<!--底部结束-->
</body>
</html>