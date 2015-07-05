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
    <title>找呀找朋友-个人主页</title>
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
				<div class="col-md-10 col-md-offset-1 white">
				  <div class="row user-self">
					<div class="col-md-3">
						<img src="
						<?php if($userinfo["face180"]): ?>__ROOT__/Uploads/Face/<?php echo ($userinfo["face180"]); ?>
						<?php else: ?>
							__PUBLIC__/Them/<?php echo ($style); ?>/images/1.jpg<?php endif; ?>
						" class='img-thumbnail'/>
						<div class="row text-center">
							<div class="col-md-4">
								<a href="<?php echo U('follow/'.$userinfo['uid']);?>" target="_blank"><h4><?php echo ($userinfo["follow"]); ?><h4/>
								<small>关注</small></a>
							</div>
							<div class="col-md-3">
								<a href="<?php echo U('fans/'.$userinfo['uid']);?>" target="_blank"><h4><?php echo ($userinfo["fans"]); ?><h4/>
								<small>粉丝</small></a>
							</div>
							<div class="col-md-4">
								<a href="<?php echo U('/'.$userinfo['uid']);?>" target="_blank"><h4><?php echo ($userinfo["weibo"]); ?><h4/>
								<small>活动</small></a>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<br/>
						<br/>
						<br/>
						<br/>
						<h3><b><?php echo ($userinfo['username']); ?></b></h3>
						<h5><?php if($userinfo["intro"]): echo ($userinfo["intro"]); else: ?>木有填写个人介绍<img src="__PUBLIC__/biaoqing/wulai.gif" alt="无赖" /><?php endif; ?></h5>
						<p><?php if($userinfo["sex"] == "1"): ?><img src='__PUBLIC__/Them/default/images/boy.png'/><?php else: ?><img src='__PUBLIC__/Them/default/images/girl.png'/><?php endif; ?> | <?php if($userinfo["lacation"]): echo ($userinfo["location"]); else: ?>未填写<?php endif; ?> | <?php if($userinfo["lacation"]): echo ($userinfo["constellation"]); else: ?>未填写<?php endif; ?></p>
						<?php if(session('uid') == $_GET['id']): ?><a href="<?php echo U('UserSetting/index');?>"><button class="btn btn-sm">修改个人资料</button></a>
							<?php else: ?>
							<?php if($isFans): ?><button type="button" class="btn btn-default disable" disabled="disabled"><span class="glyphicon glyphicon-ok"></span> 已关注　</button>
							<?php else: ?>
								<button class="btn btn-warning guanzhu"   uid='<?php echo ($userinfo["uid"]); ?>' data-toggle="modal" data-target="#guanzhu">
							  　 <span class="glyphicon glyphicon-plus"></span> 关注　
							</button><?php endif; endif; ?>
					</div>
				  </div>
				  <hr/>
				  
				  <div class="row">
					<div class="col-md-7">
						<?php if(session('uid') == $_GET['id']): ?><h3 class="text-muted">发布一条活动，寻找小伙伴一起参与吧 ^_^</h3>
								<form action="<?php echo U('Index/sendWeibo');?>" method="post">
					<a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapse1">
						<textarea class="form-control biaoqing0" cols=87 rows=2 name='content' placeholder="标题一定要长，内容一定要详，小伙伴们才愿意和你一起玩 o(∩_∩)o" required></textarea>
					</a><br>
					<div class="row">
						<div id="collapse1" class="panel-collapse collapse">
							<div class="col-md-6">
								<p><input type='text' name='activity_time' placeholder="活动开始时间" class="form-control"/></p>
								<p><input type='text' name='qq' placeholder="留个qq号，方便联系" class="form-control"/></p>
							</div>
							<div class="col-md-6">
								<p><input type='text' name='location' placeholder="活动地点，方便小伙伴们集合^_^" class="form-control"/></p>
								<p><input type='text' name='telephone' placeholder="在留个电话号码，万无一失" class="form-control"/></p>
							</div>
						</div>
					</div>
					<p>
						<div class="row">
							<div class="col-md-10">
								<h4>
									<span class='biaoqing pointer' target='biaoqing0'><img src="__PUBLIC__/Them/<?php echo ($style); ?>/images/phiz.png"/></span>　
									<a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
									  <span class="glyphicon glyphicon-picture"></span>
									</a>
								</h4>
							</div>
							<div class="col-md-2">
								<input class="btn btn-info" type="submit" value="发布">
							</div>
						</div>
						<!--上传图片位置-->
						<div class="row">
							<div id="collapseTwo" class="panel-collapse collapse">
								<input type='hidden' name='max' value='' />
								<input type='hidden' name='medium' value='' />
								<input type='hidden' name='mini' value='' />
								<input type='file' name="picture" id="picture"/>
								<img src=''  class="img-thumbnail" id='uploadPic'/>
							</div>
						</div>
					</p>
				</form><?php endif; ?>
						<hr/>
						<?php if($weibo): if(is_array($weibo)): foreach($weibo as $key=>$v): if(!$v["isturn"]): ?><!--原创微博样式开始-->
						<div class="row weibo">
							<div class="col-md-2">
								<a href="<?php echo U('/'.$v['uid']);?>" target="_blank">
								<img src="
								<?php if($v["face"]): ?>__ROOT__/Uploads/Face/<?php echo ($v["face"]); ?>
								<?php else: ?>
									__PUBLIC__/Them/<?php echo ($style); ?>/images/1.jpg<?php endif; ?>
								" width="50px" height="50px"/></a>
							</div>
							<div class="col-md-10">
								<p class="username"><a href="<?php echo U('/'.$v['uid']);?>" target="_blank"><?php echo ($v["username"]); ?></a></p>
								<p class="content"><a href="<?php echo U('Activity/index'.'?wid='.$v['id']);?>" target="_blank"><?php echo (replace_weibo($v["content"])); ?></a></p>
								<?php if($v["mini"]): ?><img src="__ROOT__/Uploads/Pic/<?php echo ($v["mini"]); ?>" class='mini'/>
									<div class='max hidden'>
										<div class='text-primary'><a href="__ROOT__/Uploads/Pic/<?php echo ($v["max"]); ?>" target='_blank'>查看大图</a></div>
										<img src="__ROOT__/Uploads/Pic/<?php echo ($v["medium"]); ?>"/>
									</div><?php endif; ?>
								<br/>
								<div class="row">
									<div class="col-md-6">
										<span class="text-muted"><?php echo (time_format($v["time"])); ?></span>
									</div>
									<div class="col-md-6">
										<span href="" class='turn pointer text-primary' id='<?php echo ($v["id"]); ?>' data-toggle="modal" data-target="#zhuanfa">转发 <?php if($v["turn"]): ?>(<?php echo ($v["turn"]); ?>)<?php endif; ?></span>
										| <span class='keep text-primary pointer' wid=<?php echo ($v["id"]); ?>>收藏<?php if($v["keep"]): ?>(<?php echo ($v["keep"]); ?>)<?php endif; ?></span> | 
										<span data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#pinglun<?php echo ($v["id"]); ?>" class='getComment text-primary pointer' wid='<?php echo ($v["id"]); ?>'>
										  评论<?php if($v["comment"]): ?>(<?php echo ($v["comment"]); ?>)<?php endif; ?>
										</span>
										<?php if($_SESSION["uid"] == $v["uid"]): ?><span class='del-weibo hidden text-primary pointer' wid='<?php echo ($v["id"]); ?>'> | 删除</span><?php endif; ?>
									</div>
								</div>
								<hr/>
								<div id="pinglun<?php echo ($v["id"]); ?>" class="panel-collapse collapse">
									<p><input type="text" class='form-control biaoqing<?php echo ($v["id"]); ?>' required /></p>
									<p class='beforComment'>
										<span class='biaoqing pointer' target='biaoqing<?php echo ($v["id"]); ?>'><img src="__PUBLIC__/Them/<?php echo ($style); ?>/images/phiz.png"/></span> <input type='checkbox'/> <button class='btn btn-warning pull-right pinglun' wid='<?php echo ($v["id"]); ?>'>评论</button> <span class='text-muted'>同时转发</span>
									</p>
								</div>
							</div>
						</div>
						<hr/>
						<!--原创微博样式结束-->
					<?php else: ?>
						<!--转发微博样式开始-->
						<div class="row weibo">
							<div class="col-md-2">
								<a href="<?php echo U('/'.$v['uid']);?>" target="_blank">
								<img src="
								<?php if($v["face"]): ?>__ROOT__/Uploads/Face/<?php echo ($v["face"]); ?>
								<?php else: ?>
									__PUBLIC__/Them/<?php echo ($style); ?>/images/1.jpg<?php endif; ?>
								" width="50px" height="50px"/></a>
							</div>
							<div class="col-md-10">
								<p class="username"><a href="<?php echo U('/'.$v['uid']);?>" target="_blank"><?php echo ($v["username"]); ?></a></p>
								<p class="content"><?php echo (replace_weibo($v["content"])); ?></p>
								
								<p>
								<div class="row">
									<div class="col-md-10 col-md-offset-1 light-gray">
										<?php if($v[isturn] == -1): ?>原活动已被删除....~_~!<img src="__PUBLIC__/biaoqing/datou.gif" alt="打头" />
										<?php else: ?>
											<p>
											<p class="usernameed"><a href="<?php echo U('/'.$v['isturn']['uid']);?>" target="_blank"><?php echo ($v["isturn"]["username"]); ?></a></p>
											<a href="<?php echo U('Activity/index'.'?wid='.$v['isturn']['id']);?>" target="_blank"><p class="contented"><?php echo (replace_weibo($v["isturn"]["content"])); ?></p></a>
											<?php if($v["isturn"]["mini"]): ?><img src="__ROOT__/Uploads/Pic/<?php echo ($v["isturn"]["mini"]); ?>" class='mini'/>
												<div class='max hidden'>
													<div class='text-primary'><a href="__ROOT__/Uploads/Pic/<?php echo ($v["isturn"]["max"]); ?>" target='_blank'>查看大图</a></div>
													<img src="__ROOT__/Uploads/Pic/<?php echo ($v["isturn"]["medium"]); ?>"/>
												</div><?php endif; ?>
											<br/>
											<div class="row">
												<div class="col-md-5">
													<span class="text-muted"><?php echo (time_format($v["isturn"]["time"])); ?></span>
												</div>
												<div class="col-md-7">
													<span class="text-primary pointer">转发 <?php if($v["isturn"]["turn"]): ?>(<?php echo ($v["isturn"]["turn"]); ?>)<?php endif; ?></span>
													| <span class="text-primary pointer">收藏<?php if($v["keep"]): ?>(<?php echo ($v["keep"]); ?>)<?php endif; ?></span> | 
													<span class="text-primary pointer">评论<?php if($v["isturn"]["comment"]): ?>(<?php echo ($v["isturn"]["comment"]); ?>)<?php endif; ?></span>
												</div>
											</div>
											</p><?php endif; ?>
									</div>
								</div>
								</p>
								
								<div class="row">
									<div class="col-md-6">
										<span class="text-muted"><?php echo (time_format($v["time"])); ?></span>
									</div>
									<div class="col-md-6">
										<span class='turned text-primary pointer' id='<?php echo ($v["id"]); ?>' tid='<?php echo ($v["isturn"]["id"]); ?>' data-toggle="modal" data-target="#zhuanfa">转发 <?php if($v["turn"]): ?>(<?php echo ($v["turn"]); ?>)<?php endif; ?></span>
										| <span class='keep text-primary pointer' wid=<?php echo ($v["id"]); ?>>收藏<?php if($v["keep"]): ?>(<?php echo ($v["keep"]); ?>)<?php endif; ?></span> | 
										<span data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#pinglun<?php echo ($v["id"]); ?>" class='getComment text-primary pointer' wid='<?php echo ($v["id"]); ?>'>
										  评论<?php if($v["comment"]): ?>(<?php echo ($v["comment"]); ?>)<?php endif; ?>
										</span>
										<?php if($_SESSION["uid"] == $v["uid"]): ?><span class='del-weibo hidden text-primary pointer' wid='<?php echo ($v["id"]); ?>'> | 删除</span><?php endif; ?>
									</div>
								</div>
								
								<hr/>
								<div id="pinglun<?php echo ($v["id"]); ?>" class="panel-collapse collapse">
									<p><input type="text" class='form-control biaoqing<?php echo ($v["id"]); ?>' required /></p>
									<p class='beforComment'>
										<span class='biaoqing pointer' target='biaoqing<?php echo ($v["id"]); ?>'><img src="__PUBLIC__/Them/<?php echo ($style); ?>/images/phiz.png"/></span> <input type='checkbox'/> <button class='btn btn-warning pull-right pinglun' wid='<?php echo ($v["id"]); ?>'>评论</button> <span class='text-muted'>同时转发</span>
									</p>
								</div>
							</div>
						</div>
						<!--转发微博样式结束-->
						<hr/><?php endif; endforeach; endif; ?>
						<p class="text-center"><?php echo ($page); ?></p>
						<?php else: ?>
							<h2 class="text-center text-info">发布一条活动，寻找小伙伴一起参与吧 ^_^<img src="__PUBLIC__/biaoqing/hanjiao.gif" alt="喊叫" /></h2><?php endif; ?>
						
					</div>
					<div class="col-md-5">
						<?php if($_GET['id'] == session('uid')): ?>我<?php else: ?>他<?php endif; ?>的关注
						<hr/>
						<div class="row text-center">
							<?php if($follow): if(is_array($follow)): foreach($follow as $key=>$v): ?><div class="col-md-3">
										<a href="<?php echo U('/'.$v['uid']);?>"><img src="
											<?php if($v["face50"]): ?>__ROOT__/Uploads/Face/<?php echo ($v["face50"]); ?>
											<?php else: ?>
												__PUBLIC__/Them/<?php echo ($style); ?>/images/1.jpg<?php endif; ?>" height="50px" width="50px"/></a>
										<p><a href="<?php echo U('/'.$v['uid']);?>"><?php echo ($v["username"]); ?></a></p>
									</div><?php endforeach; endif; ?>
							<?php else: ?>
								<span class='text-primary'>关注是一种美德</span><img src="__PUBLIC__/biaoqing/qiaomuyu.gif" alt="敲木鱼" /><?php endif; ?>
						</div>
							<?php if($_GET['id'] == session('uid')): ?>我<?php else: ?>他<?php endif; ?>的粉丝
						<hr/>
						
						<div class="row text-center">
							<?php if($fans): if(is_array($fans)): foreach($fans as $key=>$v): ?><div class="col-md-3">
										<a href="<?php echo U('/'.$v['uid']);?>"><img src="
											<?php if($v["face50"]): ?>__ROOT__/Uploads/Face/<?php echo ($v["face50"]); ?>
											<?php else: ?>
												__PUBLIC__/Them/<?php echo ($style); ?>/images/1.jpg<?php endif; ?>" height="50px" width="50px"/></a>
										<p><a href="<?php echo U('/'.$v['uid']);?>"><?php echo ($v["username"]); ?></a></p>
									</div><?php endforeach; endif; ?>
							<?php else: ?>
								<span class='text-primary'>没有粉丝的童鞋不是好童鞋</span><img src="__PUBLIC__/biaoqing/buzhidao.gif" alt="不知道" /><?php endif; ?>
						</div>
						
					</div>
				</div>
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