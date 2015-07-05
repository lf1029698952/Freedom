<?php if (!defined('THINK_PATH')) exit();?>﻿<!DOCTYPE html>
<html>
  <head>
	<?php
 $style=M('userinfo')->where(array('uid'=>session('uid')))->getField('style'); ?>
    <meta charset="utf-8">
    <meta name="Keywords" content="找呀找朋友，大学生线下活动社交平台" />
    <meta name="Description" content="找呀找朋友，大学生线下活动社交平台，运动、学习、音乐、旅行、技能、助人为乐、聚会、老乡会、情感、影视/戏剧、动漫/游戏、生活/兴趣。发布活动，找到志同道合的小伙伴一起参与吧" />
	<link rel="shortcut icon" href="__PUBLIC__/Them/default/images/icon.png">
	<link href="__PUBLIC__/Them/<?php echo ($style); ?>/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="__PUBLIC__/Them/<?php echo ($style); ?>/css/common.css" rel="stylesheet"/>
    <link href="__PUBLIC__/Uploadify/uploadify.css" rel="stylesheet">
    <script src="__PUBLIC__/js/jquery-1.10.2.min.js"></script>
    <script src="__PUBLIC__/js/bootstrap.min.js"></script>
	
    <script type='text/javascript'>
        var address = "<?php echo ($user["location"]); ?>";
        var PUBLIC = '__PUBLIC__';
        var uploadUrl = '<?php echo U("UploadFace/uploadFace");?>';
        var uploadPic = '<?php echo U("UploadFace/uploadPic");?>';
        var sid = '<?php echo session_id();?>';
        var ROOT = '__ROOT__';
        var addFollow = "<?php echo U('Common/addfollow');?>";
        var commentUrl = "<?php echo U('Index/comment');?>";
        var getComment = "<?php echo U('Index/getComment');?>";
        var delWeibo = "<?php echo U('Index/delWeibo');?>";
        var delFollow = "<?php echo U('Common/delFollow');?>";
        var keepUrl = "<?php echo U('Index/keep');?>";
        var cancelKeep = "<?php echo U('User/cancelKeep');?>";
        var delLetter = "<?php echo U('User/delLetter');?>";
        var sendComment = "<?php echo U('User/sendComment');?>";
        var delComment = "<?php echo U('User/delComment');?>";
        var searchUser = "<?php echo U('Search/searchUser');?>";
        var searchWeibo = "<?php echo U('Search/searchWeibo');?>";
        var editStyle = "<?php echo U('Common/editStyle');?>";
        var baoming = "<?php echo U('Activity/baoming');?>";
        var delBaoming = "<?php echo U('Activity/delBaoming');?>";
        var luqu = "<?php echo U('Activity/luqu');?>";
        var kaichu = "<?php echo U('Activity/kaichu');?>";
    </script>
	
	
    <script src="__PUBLIC__/js/common.js"></script>
    <script src="__PUBLIC__/Uploadify/jquery.uploadify.min.js"></script>
    <title>微博设置</title>
  </head>
  <body>
	<!--导航条开始-->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container nav-in">
			<div class="navbar-header">
				<a class="navbar-brand" href="__APP__"><img src='__PUBLIC__/Them/<?php echo ($style); ?>/images/logo-replace.png'/></a>
			</div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				  <li class="<?php if($isindex == 1): ?>active<?php endif; ?>"><a href="__APP__">首页</a></li>
				  <li class="<?php if($isletter == 1): ?>active<?php endif; ?>"><a href="<?php echo U('User/letter');?>">私信</a></li>
				  <li class="<?php if($iscomment == 1): ?>active<?php endif; ?>"><a href="<?php echo U('User/comment');?>">评论</a></li>
				  <li class="<?php if($isatme == 1): ?>active<?php endif; ?>"><a href="<?php echo U('User/atme');?>">@我</a></li>
					<form method="post" action="<?php echo U('Search/searchUser');?>" class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" name="keyword" class="form-control" placeholder="找朋友/找活动"/>
					</div>
					  <input type="submit" value="搜索" class="btn btn-default"/>
					</form>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				<li><a href="<?php echo U('/'.session('uid'));?>"><?php $username=M('userinfo')->where(array('uid'=>session('uid')))->field('username')->find(); echo $username['username']; ?></a></li>
				<li><a href="#"><span class="glyphicon glyphicon-edit"></span></a></li>
				
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-envelope"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<?php echo U('User/comment');?>">查看评论</a></li>
						<li><a href="<?php echo U('User/letter');?>">查看私信</a></li>
						<li><a href="<?php echo U('User/keep');?>">查看收藏</a></li>
						<li><a href="<?php echo U('User/atme');?>">查看@我</a></li>
					</ul>
				</li>
				
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<?php echo U('UserSetting/index');?>">帐号设置</a></li>
						<li><a class='them pointer' data-toggle="modal" data-target="#them">模版设置</a></li>
						<li><a href="<?php echo U('Index/loginOut');?>">退出登陆</a></li>
					</ul>
				</li>
				</ul>
			</div>
		</div>
	</nav>
	<!--导航条结束-->
	<!--主体内容开始-->
	<div class="container">
			<div class='row'>
				<div class="col-md-10 col-md-offset-1">
				  <div class="row white">
		<!--左边开始-->
			<?php $group=M('group')->where(array('uid'=>session('uid')))->select(); ?>
			
<div class="col-md-2 light-gray">
	<div class='row'>
		<div class='col-md-11 col-md-offset-2'>
			<br/>
			<h4><a href="__APP__"><span class="glyphicon glyphicon-home"></span> 首页</a></h4>
			<h4><a href="<?php echo U('Index/myJoin');?>"><span class="glyphicon glyphicon-random"></span> 我的参与</a></h4>
			<h4><a href="<?php echo U('User/atme');?>">@ 提到我的</a></h4>
			<h4><a href="<?php echo U('User/comment');?>"><span class="glyphicon glyphicon-comment"></span> 评论</a></h4>
			<h4><a href="<?php echo U('User/letter');?>"><span class="glyphicon glyphicon-envelope"></span> 私信</a></h4>
			<h4><a href="<?php echo U('User/keep');?>"><span class="glyphicon glyphicon-star-empty"></span> 收藏</a></h4>
			<hr/>
			<h4><a href="<?php echo U('Index/myFollow');?>"><span class="glyphicon glyphicon-star-empty"></span> 我的关注</a></h4>
			<h4><a href="<?php echo U('Index/onlyGirl');?>"><span class="glyphicon glyphicon-star-empty"></span> 只看女生</a></h4>
			<h4><a href="<?php echo U('Index/onlyBoy');?>"><span class="glyphicon glyphicon-star-empty"></span> 只看男生</a></h4>
			<hr>
			<h4><a href="__APP__"><span class="glyphicon glyphicon-map-marker"></span> 全部</a></h4>
			<?php if(is_array($group)): foreach($group as $key=>$v): ?><h4><a href="<?php echo U('Index/index',array('gid'=>$v['id']));?>"><span class="glyphicon glyphicon-map-marker"></span> <?php echo ($v["name"]); ?></a></h4><?php endforeach; endif; ?>
			<hr/>
			<button type="btn" class="btn btn-xs" data-toggle="modal" data-target="#fenzu">
				<span class='text-primary'>创建分组</span>
			</button>
			<hr/>
		</div>
	</div>
</div>
		<!--左边结束-->
		
		<!--中间开始-->
			<div class="col-md-7">
					<ul class="nav nav-tabs">
					  <li><a href="#jibenxinxi" data-toggle="tab">基本信息</a></li>
					  <li><a href="#xiugaitouxiang" data-toggle="tab">修改头像</a></li>
					  <li><a href="#xiugaimima" data-toggle="tab">修改密码</a></li>
					</ul>

					<div class="tab-content">
					  <div class="tab-pane active" id="jibenxinxi">
						<form method="post" action="<?php echo U(editBasic);?>">
						<p>
							<div class="row">
								<div class="col-md-4 text-right">昵称：</div>
								<div class="col-md-8"><input type="text" name="username" value="<?php echo ($user["username"]); ?>" class="form-control" placeholder="昵称" required autofocus/></div>
							</div>
							</p>
							<p>
							<div class="row">
								<div class="col-md-4 text-right">真实姓名：</div>
								<div class="col-md-8"><input type="text" name="truename" value="<?php echo ($user["truename"]); ?>" class="form-control" placeholder="真实姓名" required/></div>
							</div>
							</p>
							<p>
							<div class="row">
								<div class="col-md-4 text-right">性别：</div>
								<div class="col-md-8">
									<input type="radio" name="sex" value="1" <?php if($user["sex"]=="1"): ?>checked='checked'<?php endif; ?> />男
									<input type="radio" name="sex" value="0" <?php if($user["sex"]=="0"): ?>checked='checked'<?php endif; ?> />女
								</div>
							</div>
							</p>
							
							<p>
							<div class="row">
								<div class="col-md-4 text-right">所在大学：</div>
								<div class="col-md-8"><input type="text" name="location" value="<?php echo ($user["location"]); ?>" class="form-control" placeholder="所在大学" required/></div>
							</div>
							</p>
							
							<p>
							<div class="row">
								<div class="col-md-4 text-right">所在专业：</div>
								<div class="col-md-8"><input type="text" name="constellation" value="<?php echo ($user["constellation"]); ?>" class="form-control" placeholder="所在专业" required/></div>
							</div>
							</p>
							
							<p>
							<div class="row">
								<div class="col-md-4 text-right">一句话介绍自己：</div>
								<div class="col-md-8"><textarea name="intro" class="form-control" rows="3" class="form-control" placeholder="一句话介绍自己"><?php echo ($user["intro"]); ?></textarea></div>
							</div>
							</p>
								
							<p>								
							<div class="row">
								<div class="col-md-12 text-center"><input type="submit" class="btn btn-info" value="提交"/></div>
							</div>
							</p>
							
						</form>
					  </div>
					  <!--头像修改-->
					  <div class="tab-pane fade" id="xiugaitouxiang">
						<p>
							<img src="<?php if($user["face180"]): ?>__ROOT__/Uploads/Face/<?php echo ($user["face180"]); else: ?>__PUBLIC__/Them/<?php echo ($style); ?>/images/1.jpg<?php endif; ?>" id='face-image' class="center-block" width="180px" height="180px"/>
						</p>
						<p>
							<div class='row'>
								<div class='col-md-7 col-md-offset-5'>
									<input type="file" class="center-block" id="face"/>
								</div>
							</div>
							<form action="<?php echo U('UserSetting/editFace');?>" method="post">
								<input type="hidden" name='face180' value=''/>
								<input type="hidden" name='face80' value=''/>
								<input type="hidden" name='face50' value=''/>
								<input type="submit" class='btn btn-primary center-block' value='保存修改'/>
							</form>
						</p>
					  </div>
					  
					  <div class="tab-pane fade" id="xiugaimima">
					  <p>
						<form method="post" action="<?php echo U('editPwd');?>">
							<p>
								<div class="row">
									<div class="col-md-4 text-right">旧密码：</div>
									<div class="col-md-8"><input type="password" name="oldPassword" class="form-control" placeholder="旧密码" required autofocus /></div>
								</div>
							<p>
							
							<p>
								<div class="row">
									<div class="col-md-4 text-right">新密码：</div>
									<div class="col-md-8"><input type="password" name="newPassword" class="form-control" placeholder="新密码" required/></div>
								</div>
							<p>
							
							<p>
								<div class="row">
									<div class="col-md-4 text-right">确认密码：</div>
									<div class="col-md-8"><input type="password" name="rePassword" class="form-control" placeholder="确认密码" required/></div>
								</div>
							<p>
							
							<p>							
								<div class="row">
									<div class="col-md-12 text-center"><input type="submit" class="btn btn-info" value="提交"/></div>
								</div>
							<p>
						</form>
						</p>
					  </div>
					</div>
				</div>
				<hr/>
				
			</div>
		<!--中间结束-->
		</div>
		</div>
	</div>
	<!--主体内容结束-->
	<!--底部开始-->
		<script>
	var addGroup="<?php echo U('Common:addGroup');?>";
</script>

<br/>
<div class="container">
	<div id="footer" class="white">
			<div class="container">
			<!--创建分组弹出窗口开始-->
				<div class="modal fade" id="fenzu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
					  <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">创建分组</h4>
					  </div>
					  <div class="modal-body">
						<h3 class="text-center">分组名称</h3>
						<input class="form-control" id="gp-name" type="text"/>
					  </div>
					  <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="group" data-dismiss="modal">确定</button>
					  </div>
					</div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			<!--创建分组弹出窗口结束-->
			
			<?PHP
 $group=M('group')->where(array('uid'=>session('uid')))->select(); ?>
			<!--关注分组弹出窗口开始-->
				<div class="modal fade" id="guanzhu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
					  <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">关注好友</h4>
					  </div>
					  <div class="modal-body">
						<h3 class="text-center">好友分组</h3>
						<select name='gid' class="form-control">
						  <option value="0">默认分组</option>
						  <?php if(is_array($group)): foreach($group as $key=>$v): ?><option value="<?php echo ($v["id"]); ?>"><?php echo ($v["name"]); ?></option><?php endforeach; endif; ?>
						</select>
					  </div>
					  <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<input type="hidden" name="follow"/>
						<button type="button" class="btn btn-primary quedingguanzhu" data-dismiss="modal">确定</button>
					  </div>
					</div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			<!--关注分组弹出窗口结束-->
			
			<!--评论弹出窗口开始-->
				<div class="modal fade" id="pinglun" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
					  <div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">发表评论</h4>
					  </div>
					  <div class="modal-body">
						<h3 class="text-center">评论内容</h3>
						<input class="form-control" type="text"/>
						表情 <input type="checkbox"/>同时转发微博
					  </div>
					  <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
					  </div>
					</div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			<!--评论弹出窗口结束-->
			
			<!--转发弹出窗口开始-->
				<div class="modal fade" id="zhuanfa" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">转发微博</h4>
						  </div>
						<form method="post" action="<?php echo U('Index/turn');?>">
						  <div class="modal-body">
							<pre id='turn'>涛涛：大家好，很高兴见到大家</pre>
							<input type='hidden' class='wid' name='wid' value=''/>
							<input type='hidden' class='tid' name='tid' value=''/>
							<textarea class="form-control biaoqing1" name='content' required></textarea>
							<span class='biaoqing pointer' target='biaoqing1'><img src="__PUBLIC__/Them/<?php echo ($style); ?>/images/phiz.png"/></span> <input type="checkbox" name='becomment'/> <span class='turn_comment'> 同时评论给涛涛</span>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<input type="submit" value="确定" class="btn btn-primary"/>
						  </div>
						</form>
					</div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			<!--转发弹出窗口结束-->
			
			
			<!--私信弹出窗口开始-->
				<div class="modal fade" id="letter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">发送私信</h4>
						  </div>
						<form method="post" action="<?php echo U('User/sendLetter');?>">
						  <div class="modal-body">
							<input type='text' name='username' class='form-control reply-username' placeholder="用户昵称" required/>
							<br/>
							<textarea class="form-control" name='content' placeholder="内容" required autofocus></textarea>
							
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<input type="submit" value="确定" class="btn btn-primary"/>
						  </div>
						</form>
					</div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			<!--私信弹出窗口结束-->
			
			<!--模版设置弹出窗口开始-->
				<div class="modal fade" id="them" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
						  <div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">模版设置</h4>
						  </div>
						  <div class="modal-body">
							<div class='row'>
								<div class='col-md-3'>
									<img class='them-set pointer' title='default' src='__PUBLIC__/Them/<?php echo ($style); ?>/images/default.jpg'/>
								</div>
								<div class='col-md-3'>
									<img class='them-set pointer' title='style2' src='__PUBLIC__/Them/<?php echo ($style); ?>/images/style2.jpg'/>
								</div>
								<div class='col-md-3'>
									<img class='them-set pointer' title='style3' src='__PUBLIC__/Them/<?php echo ($style); ?>/images/style3.jpg'/>
								</div>
								<div class='col-md-3'>
									<img class='them-set pointer' title='style4' src='__PUBLIC__/Them/<?php echo ($style); ?>/images/style4.jpg'/>
								</div>
							</div>
						  </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="submit" them='' class="btn btn-primary them-yes">确定</button>
						  </div>
					</div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
			<!--模版设置弹出窗口结束-->
			
			
			<!--表情弹出框开始-->
			
			<div class="panel panel-default biaoqingkuang hidden" target='' style="width:500px;position:absolute;left:100px;top:100px;">
			  <div class="panel-heading">
				<h3 class="panel-title">表情</h3>
			  </div>
			  <div class="panel-body">
					<img src="__PUBLIC__/biaoqing/chi.gif" alt="吃" />
					<img src="__PUBLIC__/biaoqing/chouyan.gif" alt="抽烟" />
					<img src="__PUBLIC__/biaoqing/dadianhua.gif" alt="打电话" />
					<img src="__PUBLIC__/biaoqing/dahan.gif" alt="大喊" />
					<img src="__PUBLIC__/biaoqing/datou.gif" alt="打头" />
					<img src="__PUBLIC__/biaoqing/daxiao.gif" alt="大笑" />
					<img src="__PUBLIC__/biaoqing/diao.gif" alt="叼" />
					<img src="__PUBLIC__/biaoqing/erye.gif" alt="二耶" />
					<img src="__PUBLIC__/biaoqing/fadai.gif" alt="发呆" />
					<img src="__PUBLIC__/biaoqing/fannao.gif" alt="烦恼" />
					<img src="__PUBLIC__/biaoqing/fengzui.gif" alt="封嘴" />
					<img src="__PUBLIC__/biaoqing/gaoxin.gif" alt="高兴" />
					<img src="__PUBLIC__/biaoqing/gantan.gif" alt="感叹" />
					<img src="__PUBLIC__/biaoqing/guai.gif" alt="乖" />
					<img src="__PUBLIC__/biaoqing/guilian.gif" alt="鬼脸" />
					<img src="__PUBLIC__/biaoqing/hanjiao.gif" alt="喊叫" />
					<img src="__PUBLIC__/biaoqing/guzhang.gif" alt="鼓掌" />
					<img src="__PUBLIC__/biaoqing/hejiu.gif" alt="喝酒" />
					<img src="__PUBLIC__/biaoqing/hen.gif" alt="恨" />
					<img src="__PUBLIC__/biaoqing/hengaoxin.gif" alt="很高兴" />
					<img src="__PUBLIC__/biaoqing/jingxia.gif" alt="惊吓" />
					<img src="__PUBLIC__/biaoqing/koushui.gif" alt="口水" />
					<img src="__PUBLIC__/biaoqing/daku.gif" alt="大哭" />
					<img src="__PUBLIC__/biaoqing/lianhong.gif" alt="脸红" />
					<img src="__PUBLIC__/biaoqing/liuhan.gif" alt="流汗" />
					<img src="__PUBLIC__/biaoqing/maimeng.gif" alt="卖萌" />
					<img src="__PUBLIC__/biaoqing/ninini.gif" alt="你你你" />
					<img src="__PUBLIC__/biaoqing/nvtianshi.gif" alt="女天使" />
					<img src="__PUBLIC__/biaoqing/outu.gif" alt="呕吐" />
					<img src="__PUBLIC__/biaoqing/qiang.gif" alt="强" />
					<img src="__PUBLIC__/biaoqing/qiaomuyu.gif" alt="敲木鱼" />
					<img src="__PUBLIC__/biaoqing/qianshui.gif" alt="浅睡" />
					<img src="__PUBLIC__/biaoqing/qingqing.gif" alt="亲亲" />
					<img src="__PUBLIC__/biaoqing/qingzhu.gif" alt="庆祝" />
					<img src="__PUBLIC__/biaoqing/se.gif" alt="色" />
					<img src="__PUBLIC__/biaoqing/shajiao.gif" alt="撒娇" />
					<img src="__PUBLIC__/biaoqing/shanren.gif" alt="闪人" />
					<img src="__PUBLIC__/biaoqing/shengshetou.gif" alt="伸舌头" />
					<img src="__PUBLIC__/biaoqing/shengshui.gif" alt="深睡" />
					<img src="__PUBLIC__/biaoqing/tianshi.gif" alt="天使" />
					<img src="__PUBLIC__/biaoqing/tiao.gif" alt="跳" />
					<img src="__PUBLIC__/biaoqing/tongyi.gif" alt="同意" />
					<img src="__PUBLIC__/biaoqing/wulai.gif" alt="无赖" />
					<img src="__PUBLIC__/biaoqing/wushuowei.gif" alt="无所谓" />
					<img src="__PUBLIC__/biaoqing/xiangshang.gif" alt="向上" />
					<img src="__PUBLIC__/biaoqing/xiangyou.gif" alt="向右" />
					<img src="__PUBLIC__/biaoqing/xianhua.gif" alt="鲜花" />
					<img src="__PUBLIC__/biaoqing/buzhidao.gif" alt="不知道" />
					<img src="__PUBLIC__/biaoqing/yukuai.gif" alt="愉快" />
					<img src="__PUBLIC__/biaoqing/yinxiao.gif" alt="阴笑" />
					<img src="__PUBLIC__/biaoqing/ye.gif" alt="耶" />
					<img src="__PUBLIC__/biaoqing/xiong.gif" alt="凶" />
					<img src="__PUBLIC__/biaoqing/xiexing.gif" alt="写信" />
					<img src="__PUBLIC__/biaoqing/xiaozhu.gif" alt="小猪" />
					<img src="__PUBLIC__/biaoqing/xiaotianshi.gif" alt="笑天使" />
					<img src="__PUBLIC__/biaoqing/xiaoku.gif" alt="小哭" />
			  </div>
			</div>
			<!--表情弹出框结束-->
			
				<div class="row">
					<div class="col-md-10 col-md-offset-1 text-center text-muted">
						<p>版权信息：@<a href="http://www.luojiangtao.com" class="text-muted" target="_blank">罗江涛</a> 西南大学 2012级计算机科学与技术学院</p>
						<p>QQ:1368761119　　　2014/1/11-2014/4/8</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--底部结束-->
	
  </body>
</html>