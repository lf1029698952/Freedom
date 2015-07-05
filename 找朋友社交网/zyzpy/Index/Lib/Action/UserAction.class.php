<?php
	//用户个人页面控制器
	class UserAction extends CommonAction{
		public function index(){
			$id=$this->_get('id','intval');
			//读取用户个人信息
			$where=array('uid'=>$id);
			$userinfo=M('userinfo')->where($where)->field('truename,face50,face80,style',true)->find();
			if(!$userinfo){
				header('Content-Type:text/html;Charset=UTF-8');
				redirect(__ROOT__,3,'用户不存在，正在为您跳转到首页....');
				exit();
			}
			//p($userinfo);die;
			$this->userinfo=$userinfo;
			//读取微博信息
			$db=D('WeiboView');
			import('ORG.Util.Page');
			$where=array('uid'=>$id);
			
			//分页
			$count=$db->where($where)->count();
			$page=new Page($count, 20);
			$limit=$page->firstRow.','.$page->listRows;
			
			$weibo=$db->getAll($where,$limit);
			$this->page=$page->show();
			$this->weibo=$weibo;
			
			//读取用户的关注和粉丝
			$db=M('follow');
			//读取关注
			$follow=$db->where(array('fans'=>$id))->field('follow')->limit(8)->select();
			if($follow){
				foreach($follow as $k=>$v){
					$follow[$k]=$v['follow'];
				}
				
				$userinfo=M('userinfo');
				foreach($follow as $k=>$v){
					$follow[$k]=$userinfo->where(array('uid'=>$v))->field(array('uid','face50','username'))->find();
				}
			}
			
			//读取粉丝
			$fans=$db->where(array('follow'=>$id))->field('fans')->limit(8)->select();
			if($fans){
				foreach($fans as $k=>$v){
					$fans[$k]=$v['fans'];
				}
				
				$userinfo=M('userinfo');
				foreach($fans as $k=>$v){
					$fans[$k]=$userinfo->where(array('uid'=>$v))->field(array('uid','face50','username'))->find();
				}
			}

			//查询是否已关注该用户
			$isFans = M('follow')->where(array('fans'=>session('uid'),'follow'=>$id))->find();

			if($isFans['follow']){
				$isFans = 1;
			}else{
				$isFans = 0;
			}
			
			$this->isFans=$isFans;
			$this->follow=$follow;
			$this->fans=$fans;
			
			$this->display();
		}
		
		public function _empty($name){
			$this->_getUrl($name);
		}
		
		private function _getUrl($name){
			$name=htmlspecialchars($name);
			$where=array('username'=>$name);
			$uid=M('userinfo')->where($where)->getField('uid');
			if(!$uid){
				redirect(U('Index/index'));
			}else{
				redirect(U('/'.$uid));
			}
		}
		
		//关注与粉丝
		public function followList(){
			$uid=$this->_get('uid','intval');
			$type=$this->_get('type','intval');
			
			import('ORG.Util.Page');
			$db=M('follow');
			$where=$type ? array('fans'=>$uid) : array('follow'=> $uid);
			$field=$type ? 'follow' : 'fans';
			$count = $db->where($where)->count();
			$page=new Page($count,20);
			$limit=$page->firstRow.','.$page->listRows;
			$uids=$db->field($field)->where($where)->limit($limit)->select();
			
			if($uids){
				foreach($uids as $k=>$v){
					$uids[$k] = $type ? $v['follow'] : $v['fans'];
				}
				$where=array('uid'=>array('IN',$uids));
				$field=array('face50'=>'face','username','sex','location','follow','fans','weibo','uid');
				
				$users=M('userinfo')->where($where)->field($field)->select();
				$this->users=$users;
			}
			
			$where=array('fans'=>session('uid'));
			$follow=$db->field('follow')->where($where)->select();
			
			if($follow){
				foreach($follow as $k=>$v){
					$follow[$k]=$v['follow'];
				}
			}
			
			$where=array('follow'=>session('uid'));
			$fans=$db->field('fans')->where($where)->select();
			
			if($fans){
				foreach($fans as $k=>$v){
					$fans[$k]=$v['fans'];
				}
			}
			$this->type=$type;
			$this->count=$count;
			$this->follow=$follow;
			$this->fans=$fans;
			$this->page=$page->show();
			$this->display();
		}
		
		//收藏列表
		public function keep(){
			$uid=session('uid');
			$where=array('keep.uid'=>$uid);
			
			//分页
			import('ORG.Util.Page');
			$count=M('keep')->where(array('uid'=>$uid))->count();
			$page=new Page($count,20);
			$limit=$page->firstRow.','.$page->listRows;
			
			
			$weibo=D('KeepView')->getAll($where,$limit);
			$this->weibo=$weibo;
			$this->display('weiboList');
		}
		
		//异步取消收藏
		public function cancelKeep(){
			if(!$this->isAjax()){
				halt('页面不存在..');
			}
			$wid=$this->_post('wid','intval');
			$kid=$this->_post('kid','intval');
			
			if(M('keep')->delete($kid)){
				M('weibo')->where(array('id'=>$wid))->setDec('keep');
				echo 1;
			}else{
				echo 0;
			}
		}
		
		//私信视图
		public function letter(){
			
			//分页
			$where=array('uid'=>session('uid'));
			
			import('ORG.Util.Page');
			$count=M('letter')->where($where)->count();
			$page=new Page($count,20);
			$limit=$page->firstRow.','.$page->listRows;
			
			$where=array('letter.uid'=>session('uid'));
			$letter=D('LetterView')->where($where)->order('time DESC')->limit($limit)->select();
			$this->letter=$letter;
			//用于判断导航条哪个是激活状态
			$this->isletter=1;
			$this->count=$count;
			$this->page=$page->show();
			$this->display();
		}
		
		//发送私信
		public function sendLetter(){
			if(!$this->isPost()){
				halt('页面不存在..');
			}
			
			$username=$this->_post('username');
			$uid=M('userinfo')->where(array('username'=>$username))->field('uid')->find();
			
			if(!$uid){
				$this->error('该用户不存在');
			}
			$data=array(
				'from'=>session('uid'),
				'content'=>$this->_post('content'),
				'time'=>time(),
				'uid'=>$uid['uid']
			);
			if(M('letter')->add($data)){
				$this->success('发送成功');
			}else{
				$this->error('发送失败');
			}
		}
		
		//异步删除私信
		public function delLetter(){
			if(!$this->isAjax()){
				halt('页面不存在..');
			}
			
			$id=$this->_post('id','intval');
			if(M('letter')->where(array('id'=>$id))->delete()){
				echo 1;
			}else{
				echo 0;
			}
		}
		
		//评论视图
		public function comment(){
			//分页
			import('ORG.Util.Page');
			$count=D('CommentView')->where(array('uid'=>session('uid')))->count();
			$page=new Page($count, 20);
			$limit=$page->firstRow.','.$page->listRows;
			
			$comment=D('CommentView')->where(array('uid'=>session('uid')))->order('time DESC')->limit($limit)->select();
			$this->comment=$comment;
			$this->iscomment=1;
			$this->count=$count;
			$this->page = $page->show();
			
			$this->display();
		}
		
		//异步发送评论
		public function sendComment(){
			if(!$this->isAjax()){
				halt('页面不存在..');
			}
			$wid=$this->_post('wid');
			
			$data=array(
				'wid'=>$wid,
				'time'=>time(),
				'content'=>$this->_post('content'),
				'uid'=>session('uid')
			);
			p($data);
			
			if(M('comment')->add($data)){
				M('weibo')->where(array('id'=>$wid))->setInc('comment');
				echo 1;
			}else{
				echo 0;
			}
		}
		
		//异步删除评论
		public function delComment(){
			if(!$this->isAjax()){
				halt('页面不存在..');
			}
			$wid=$this->_post('wid','intval');
			$cid=$this->_post('cid','intval');
			
			if(M('comment')->delete($cid)){
				M('weibo')->where(array('id'=>$wid))->setDec('comment');
				echo 1;
			}else{
				echo 0;
			}
		}
		
		//atme视图
		public function atme(){
			$where=array('uid'=>session('uid'));
			$wid=M('atme')->where($where)->field('wid')->select();
			if($wid){
				foreach($wid as $k=>$v){
					$wid[$k]=$v['wid'];
				}
			}
			
			import('ORG.Util.Page');
			$count=count($wid);
			$page=new Page($count,20);
			$limit=$page->firstRow.','.$page->listRows;
			
			$where=array('id'=>array('IN',$wid));
			$weibo=D('WeiboView')->getAll($where,$limit);
			
			$this->weibo=$weibo;
			$this->isatme=1;
			$this->page=$page->show();
			$this->display('weiboList');
		}
	}
?>