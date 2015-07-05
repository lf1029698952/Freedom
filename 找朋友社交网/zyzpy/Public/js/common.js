
$(function(){
	//点击刷新验证码
	var url=$('#verify').attr('src');
	$('#verify').click(function(){
		$(this).attr('src',url+'?id='+Math.random());
	});
	
	//点击切换到大图
	$('.mini').click(function(){
		$(this).addClass('hidden mini').next().removeClass('hidden');
	});
	//点击切换到小图
	$('.max').click(function(){
		$(this).addClass('hidden max').prev().removeClass('hidden');
	});
	
	var target;
	//表情框弹出处理
	$('.biaoqing').click(function(e){
		var target=$(this).prev();
		var x=e.pageX+'px';
		var y=(e.pageY+20)+'px';
		if(!$('.biaoqingkuang').is(':hidden')){
			$('.biaoqingkuang').addClass('hidden');
		}else{
			$('.biaoqingkuang').css({'top':y,'left':x}).removeClass('hidden');
			$('.biaoqingkuang').attr('target',$(this).attr('target'));
		}
	});
	/*点击表情，添加alt到文本框*/
	$('.panel-body img').click(function(){
		var biaoqing = '['+$(this).attr('alt')+']';
		var target='.'+$('.biaoqingkuang').attr('target');
		target=$(target);
		target.val(target.val()+biaoqing);
		$('.biaoqingkuang').addClass('hidden');
	});
	
	//回复按钮
	$('.huifu').live('click',function(){
		var target='.biaoqing'+$(this).attr('target');
		var username=$(this).attr('username');
		var obj=$(this).parents().parents().prev('input').html();
		
		$(target).val('@'+username+' : ');
	});
	
	//头像上传 Uploadify 插件
	$('#face').uploadify({
		swf : PUBLIC + '/Uploadify/uploadify.swf',	//引入Uploadify核心Flash文件
		uploader : uploadUrl,	//PHP处理脚本地址
		width : 120,	//上传按钮宽度
		height : 30,	//上传按钮高度
		buttonImage : PUBLIC + '/Uploadify/browse-btn.png',	//上传按钮背景图地址
		fileTypeDesc : 'Image File',	//选择文件提示文字
		fileTypeExts : '*.jpeg; *.jpg; *.png; *.gif',	//允许选择的文件类型
		formData : {'session_id' : sid},
		//上传成功后的回调函数
		onUploadSuccess : function(file,data,response){
			eval('var data='+data);
			if(data.status){
				$('#face-image').attr('src',ROOT+'/Uploads/Face/'+data.path.max);
				$('input[name=face180]').val(data.path.max);
				$('input[name=face80]').val(data.path.medium);
				$('input[name=face50]').val(data.path.mini);
			}else{
				alert(data.msg);
			}
		}
	});
	
	//图片上传 Uploadify 插件
	$('#picture').uploadify({
		swf : PUBLIC + '/Uploadify/uploadify.swf',	//引入Uploadify核心Flash文件
		uploader : uploadPic,	//PHP处理脚本地址
		width : 120,	//上传按钮宽度
		height : 30,	//上传按钮高度
		buttonImage : PUBLIC + '/Uploadify/browse-btn.png',	//上传按钮背景图地址
		fileTypeDesc : 'Image File',	//选择文件提示文字
		fileTypeExts : '*.jpeg; *.jpg; *.png; *.gif',	//允许选择的文件类型
		formData : {'session_id' : sid},
		//上传成功后的回调函数
		onUploadSuccess : function(file,data,response){
			eval('var data='+data);
			if(data.status){
				$('#uploadPic').attr('src',ROOT+'/Uploads/Pic/'+data.path.mini);
				$('input[name=max]').val(data.path.max);
				$('input[name=medium]').val(data.path.medium);
				$('input[name=mini]').val(data.path.mini);
			}else{
				alert(data.msg);
			}
		}
	});
	
	//异步创建分组
	$('#group').click(function(){
		var groupName=$('#gp-name').val();
		if(groupName!=''){
			$.post(addGroup,{name:groupName},function(data){
				if(data.status){
					alert(data.msg);
				}else{
					alert(data.msg);
				}
			},'json')
		}
	});
	
	//异步收藏
	$('.keep').click(function(){
		var wid=$(this).attr('wid');
		
		
		$.post(keepUrl,{wid:wid},function(data){
			if(data == 1){
				alert('收藏成功');
			}
			if(data == -1){
				alert('已收藏');
			}
			if(data == 0){
				alert('收藏失败');
			}
		},'json');
	});
	
	//点击关注时变换弹出框隐藏输入框属性
	$('.guanzhu').click(function(){
		$('input[name=follow]').val($(this).attr('uid'));
	});
	
	//异步添加关注
	$('.quedingguanzhu').click(function(){
		var follow=$('input[name=follow]').val();
		var group=$('select[name=gid]').val();
		$.post(addFollow,{
			'follow':follow,
			'gid':group
		},function(data){
			if(data.status){
				$('.guanzhu[uid='+follow+']').attr('class','btn btn-default disable').html('<span class="glyphicon glyphicon-ok"></span> 已关注　').attr('disabled','disabled');
				window.location.reload();
			}else{
				alert(data.msg);
			}
		},'json');
	});
	
	/*
	*转发原创微博处理
	*/
	$('.turn').click(function(){
		var content=$(this).parents().parents().siblings('.content').html();
		var author=$(this).parents().parents().siblings('.username').html();
		var wid=$(this).attr('id');
		$('.wid').val(wid);
		$('.tid').val(0);
		$('#turn').html(author+':'+content);
		$('.turn_comment').html('同时评论给'+author);
	});
	
	//转发已经转发微博处理
	$('.turned').click(function(){
		var usernameed=$(this).parents().parents().prev().prev().children().children('.usernameed').html();
		var contented=$(this).parents().parents().prev().prev().children().children('.contented').html();
		var content=$(this).parents().parents().prev().prev().siblings('.content').html();
		var wid=$(this).attr('id');
		var tid=$(this).attr('tid');
		$('.wid').val(wid);
		$('.tid').val(tid);
		$('#turn').html(usernameed+':'+contented);
		$('.turn_comment').html('同时评论给'+usernameed);
		$('textarea').html(replace_weibo('// @'+usernameed+' : '+content));
	});
	
	/**
	 * 替换微博内容，去除 <a> 链接与表情图片
	 */
	function replace_weibo (content) {
		content = content.replace(/<img.*?title=['"](.*?)['"].*?\/?>/ig, '[$1]');
		content = content.replace(/<a.*?>(.*?)<\/a>/ig, '$1');
		return content.replace(/<span.*?>\&nbsp;(\/\/)\&nbsp;<\/span>/ig, '$1');
	}
	
	//异步评论
	$('.pinglun').click(function(){
		var commentList=$('.beforComment');
		var content=$(this).parents().prev().children().val();
		var isturn=$(this).prev().is(":checked")?1:0;
		var wid=$(this).attr('wid');
		
		var cons={
			content:content,
			wid:wid,
			isturn:isturn
		};
		if(content == ''){
			return false;
		}
		$.post(commentUrl,cons,function(data){
			if(data){
				if(isturn){
					window.location.reload();
				}else{
					commentList.append(data);
					$('.form-control').val('');
				}
			}else{
				alert('评论失败');
			}
			
		},'html');
	});
	//异步加载评论
	$('.getComment').click(function(){
		$('.comment-list').remove().hide();
	
		var wid=$(this).attr('wid');
		var page=$(this).attr('page');
		var beforComment=$(this).parents().parents().next().next().children('.beforComment');
		var data1={
			wid:wid,
			page:page
		};
		
		$.post(getComment,data1,function(data){
			if(data){
				beforComment.append(data);
			}else{
				alert('获取评论失败...请重试');
			}
			
		},'html');
	});
	
	//异步分页处理
	$('.comment-page').live('click',function(){
		var wid=$(this).attr('wid');
		var page=$(this).attr('page');
		var beforComment=$(this).parents().parents().children('.beforComment');
		var data1={
			wid:wid,
			page:page
		};
		
		$.post(getComment,data1,function(data){
			if(data){
				$('.comment-list').prev('hr').remove();
				$('.comment-list').remove().hide();
				beforComment.append(data);
			}else{
				alert('获取评论失败...请重试');
			}
			
		},'html');
	});
	
	//删除按钮的显示与隐藏
	$('.weibo').hover(function(){
		$(this).find('.del-weibo').removeClass('hidden');
	},function(){
		$(this).find('.del-weibo').addClass('hidden');
	});
	//异步删除微博
	$('.del-weibo').click(function(){
		var wid=$(this).attr('wid');
		var isDel=confirm('确认要删除微博？');
		var obj=$(this).parents().parents().parents().parents('.weibo');
		if(isDel){
			$.post(delWeibo,{wid:wid},function(data){
				if(data){
					obj.slideUp('slow',function(){
						obj.remove();
					});
				}else{
					alert('删除失败...');
				}
			},'json');
		}
	});
	
	//异步删除关注与粉丝
	$('.del-follow').click(function(){
		var data={
			uid : $(this).attr('uid'),
			type : $(this).attr('type')
		};
		var isDel=confirm('确认移除？');
		var obj=$(this).parents('.follow');
		
		if(isDel){
			$.post(delFollow,data,function(data){
				if(data){
					obj.slideUp('slow',function(){
						obj.remove();
					})
				}else{
					alert("移除失败....");
				}
			},'json');
		}
	});
	
	//异步取消收藏
	$('.cancel-keep').click(function(){
		var data={
			wid : $(this).attr('wid'),
			kid : $(this).attr('kid')
		};
		
		var obj=$(this).parents('.weibo');
		
		var isDel=confirm('确认取消收藏？');
		
		if(isDel){
			$.post(cancelKeep,data,function(){
				if(data){
					obj.slideUp('slow',function(){
						obj.remove();
					});
				}else{
					alert('取消失败...');
				}
			},'json');
		}
	});
	
	//异步删除私信
	$('.del-letter').click(function(){
		data={
			id:$(this).attr('id'),
		};
		
		var isDel=confirm('确认删除');
		var obj=$(this).parents('.letter');
		if(isDel){
			$.post(delLetter,data,function(){
				if(data){
					obj.slideUp('slow',function(){
						obj.remove();
					});
				}else{
					alert('删除失败');
				}
			},'json');
		}
	});
	
	//回复私信弹出框获得用户名
	$('.reply-letter').click(function(){
		var username=$(this).attr('username');
		$('.reply-username').val(username);
	});
	
	//异步回复
	$('.huifu').click(function(){
		var wid=$(this).attr('wid');
		
		data={
			wid:$(this).attr('wid'),
			content:$(this).parents().prev().children().val()
		};
		
		$.post(sendComment,data,function(data){
			alert(data);
		},'json');
	});
	
	//异步回复
	$('.del-comment').click(function(){
		
		var obj=$(this).parents('.comment');
		data={
			wid:$(this).attr('wid'),
			cid:$(this).attr('cid')
		};
		
		var isDel=confirm('确认删除？');
		if(isDel){
			$.post(delComment,data,function(data){
				if(data){
					obj.slideUp('slow',function(){
						obj.remove();
					});
				}else{
					alert('删除失败....');
				}
			},'json');
		}
	});
	
	//搜索 微博/找人按钮内容和提交地址变换
	$('.search-weibo').click(function(){
		$('.search').html('找微博');
		$('.search-url').attr('action',searchWeibo);
	});
	
	//搜索 微博/找人按钮内容和提交地址变换
	$('.search-user').click(function(){
		$('.search').html('找人');
		$('.search-url').attr('action',searchUser);
	});
	
	//模版设置点击效果和提交按钮的值
	$('.them-set').click(function(){
		var them=$(this).attr('title');
		$('.them-set').removeClass('thumbnail');
		$(this).addClass('thumbnail');
		$('.them-yes').attr('them',them);
	});
	//模版设置确定按钮点击
	$('.them-yes').click(function(){
		var them=$(this).attr('them');
		if(!them){
			return false;
		}
		$.post(editStyle,{style:them},function(data){
			if(data){
				window.location.reload();
			}else{
				alert('模版设置失败...请重试');
			}
		},'json');
	});


	//异步报名和取消报名
	$('.baoming').click(function(){
		var wid = $(this).attr('wid');
		var baoming_data = {
			wid:wid
		};
		$.post(baoming,baoming_data,function(data){
			if(data == 1){
				alert('亲，报名成功啦 ^_^，等着小伙伴的消息吧，么么哒');
				window.location.reload();
			}else if(data == 0){
				alert('亲，报名失败了，~_~，重试一下下嘛。。。');
			}else if(data == -1){
				var isDel = confirm('亲，您已经报名了，您要取消报名吗，思密达~_~!');

				if(isDel){
					$.post(delBaoming,baoming_data,function(data2){
						if(data2 == 1){
							alert('亲，取消报名成功啦 ^_^ 思密达');
							window.location.reload();
						}else{
							alert('亲，取消报名失败了，~_~，重试一下下嘛。。。');
						}
					},'json');
				}
			}
		},'json');
	});

	//异步录取
	$('.luqu').click(function(){
		var data = {
			'uid':$(this).attr('uid'),
			'wid':$(this).attr('wid'),
		}

		$.post(luqu,data,function(data){
			if(data){
				alert('录取成功，么么哒o(∩_∩)o');
				window.location.reload();
			}else{
				alert('录取失败，思密达-_-。sorry！');
			}
		},'json');
	});

	//异步开除
	$('.kaichu').click(function(){
		var data = {
			'uid':$(this).attr('uid'),
			'wid':$(this).attr('wid'),
		}

		$.post(kaichu,data,function(data){
			if(data){
				alert('开除成功，么么哒\\(^o^)/');
				window.location.reload();
			}else{
				alert('开除失败，思密达⊙﹏⊙');
			}
		},'json');
	});

});