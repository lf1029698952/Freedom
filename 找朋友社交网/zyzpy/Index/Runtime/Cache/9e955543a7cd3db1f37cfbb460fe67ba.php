<?php if (!defined('THINK_PATH')) exit();?>﻿<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta property="qc:admins" content="17205166666212016375" />
    <meta name="Keywords" content="找呀找朋友，大学生线下活动社交平台" />
    <meta name="Description" content="找呀找朋友，大学生线下活动社交平台，运动、学习、音乐、旅行、技能、助人为乐、聚会、老乡会、情感、影视/戏剧、动漫/游戏、生活/兴趣。发布活动，找到志同道合的小伙伴一起参与吧" />
	<link rel="shortcut icon" href="__PUBLIC__/Them/default/images/icon.png">
	<link href="__PUBLIC__/Them/default/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="__PUBLIC__/Them/default/css/login.css" rel="stylesheet"/>
    <script src="__PUBLIC__/js/jquery-1.10.2.min.js"></script>
    <script src="__PUBLIC__/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101053568" data-redirecturi="http://www.zyzpy.com/zyzpy/qc_callback.html" charset="utf-8"></script>

    <title>找呀找朋友</title>
  </head>

<body>

<div class="container" style="margin-top:80px;">
	<div class="row">
		<div class="col-md-4 col-md-offset-4 text-center">
			<form method="post" action="<?php echo U('login');?>" class="form-signin" role="form">
				<p><input type="text" name="username" class="form-control" placeholder="用户名" required autofocus></p>
				<p><input type="password" name="password" class="form-control" placeholder="密码" required></p>
				<p>
					<label class="checkbox">
					<input type="checkbox" name="auto" value="1"> 下次自动登录
					</label>
				</p>
				<a href="<?php echo U('register');?>"><span class="btn btn-primary">注册</span></a>　　　　　<button class="btn btn-primary" type="submit">登录</button>
			</form>
		</div>
	</div>
</div>

/*<script type="text/javascript">

	var QQLogin = "<?php echo U('QQLogin/index');?>";
	$(function(){
		$('.qqlogin').click(function(){
			var data = {
				username:'taotao4',
				face : 'http://q.qlogo.cn/qqapp/101053568/3371FA3E3BA2AFEF819C377729EF3189/100',
				openid : '3371FA3E3BA2AFEF819C377729EF31894',
				accesstoken : 'A6C37BC2DBE2F06CF3F46AEF6DFD7301',
				gender : '男'
			};
			$.post(QQLogin,data,function(data){
				alert(data);
			},'json');
		});
	})
</script>*/

<!-- <button class="btn btn-primary btn-block btn-lg qqlogin">测试</button> -->


<img src="__PUBLIC__/Them/default/images/logo_login.png" alt="找呀找朋友" style="position:fixed;top:380px;left:30px;" />
<img src="__PUBLIC__/Them/default/images/introduce.png" alt="找呀找朋友" style="position:fixed;top:50px;left:1200px;" />


<p class="text-center"><span id="qqLoginBtn"></span></p>
<script type="text/javascript">
   /*调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中*/
   QC.Login({
      /*btnId：插入按钮的节点id，必选*/
       btnId:"qqLoginBtn",    
      /*用户需要确认的scope授权项，可选，默认all*/
       scope:"all",
      /*按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S*/
       size: "A_XL"
   }, function(reqData, opts){/*登录成功*/
      /*根据返回数据，更换按钮显示状态方法*/
       var dom = document.getElementById(opts['btnId']),
       _logoutTemplate=[
            /*头像*/
            '<span><img src="{figureurl}" class="{size_key}"/></span>',
            /*昵称*/
            '<span>{nickname}</span>',
            /*退出*/
            '<span><a href="javascript:QC.Login.signOut();">退出</a></span>'    
       ].join("");
       dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
           nickname : QC.String.escHTML(reqData.nickname), /*做xss过滤*/
           figureurl : reqData.figureurl
       }));
   }, function(opts){/*注销成功*/
         alert('QQ登录 注销成功');
   }
);
</script>


<script type="text/javascript">
		/*从页面收集OpenAPI必要的参数。get_user_info不需要输入参数，因此paras中没有参数*/
		var paras = {};

		/*用JS SDK调用OpenAPI*/
		QC.api("get_user_info", paras)
			/*指定接口访问成功的接收函数，s为成功返回Response对象*/
			.success(function(s){
				/*成功回调，通过s.data获取OpenAPI的返回数据*/
				/*alert("获取用户信息成功！当前用户昵称为："+s.data.nickname);
				alert(s.data.gender);*/
				
				QC.Login.getMe(function(openId, accessToken){
					/*alert(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken].join("\n"));*/
					var userinfo = {
						username:s.data.nickname,
						face:s.data.figureurl_qq_2,
						openid:openId,
						accesstoken:accessToken,
						gender:s.data.gender
					};
					
					$.post("http://www.zyzpy.com/zyzpy/index.php/QQLogin/index",userinfo,function(data){
						if(data){
							/*alert ("登录成功，么么哒o(∩_∩)o");*/
							window.location.href="http://www.zyzpy.com";
						}else{
							alert("登录失败，思密达 ~>_<~");
						}
					},'json');
				});
				
			})
			/*指定接口访问失败的接收函数，f为失败返回Response对象*/
			.error(function(f){
				/*失败回调*/
				alert("获取用户信息失败！");
			})
			/*指定接口完成请求后的接收函数，c为完成请求返回Response对象*/
			.complete(function(c){
				/*完成请求回调*/
				/*alert("获取用户信息完成！");*/
			});
		
</script>



<script type="text/javascript">
if(QC.Login.check()){/*如果已登录*/
	QC.Login.getMe(function(openId, accessToken){
		/*alert(["当前登录用户的", "openId为："+openId, "accessToken为："+accessToken].join("n"));
		window.location.href="http://www.zyzpy.com";*/
	});
	/*这里可以调用自己的保存接口*/
}
</script>
</div>

	<!--底部开始-->
	
	<div id="footer" style='margin-top:120px;'>
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1 text-center" style="color:#fff">
					<p>版权信息：@<a href="http://www.yule1234.com" target="_blank" style="color:#fff">李峰</a> 西南大学 2012级计算机科学与技术学院</p>
					<p>QQ:1029698952　　　2014/1/11-2014/4/8</p>
				</div>
			</div>
		</div>
    </div>
	<!--底部结束-->
</body>
</html>