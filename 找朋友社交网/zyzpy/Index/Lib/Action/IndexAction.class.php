<?php
// 本类由系统自动生成，仅供测试用途
class IndexAction extends CommonAction {
	//主页面视图
    public function index(){
		
		$db=D('WeiboView');
		import('ORG.Util.Page');
		$where=array('fans'=>session('uid'));
		$uid=array(session('uid'));
		
		if(isset($_GET['gid'])){
			$gid=$this->_get('gid', 'intval');
			$where['gid']=$gid;
			$uid=array();
		}
		
		$result=M('follow')->where($where)->field('follow')->select();
		//p($result);die;
		//组合我和我关注的人的uid
		if($result){
			foreach($result as $k=>$v){
				$uid[]=$v['follow'];
			}
		}
		$where=array('uid'=>array('IN',$uid));


		$where='';
		
		//分页
		$count=$db->where($where)->count();
		$page=new Page($count, 20);
		$limit=$page->firstRow.','.$page->listRows;
		
		
		$weibo=$db->getAll($where,$limit);
		//p($weibo);die;
		$this->page=$page->show();
		$this->weibo=$weibo;
		$this->isindex=1;
		//p($weibo);die;
		$this->display();
    }


    //我的参与
    public function myJoin(){
		
		$db=D('WeiboView');
		import('ORG.Util.Page');
		$where=array('fans'=>session('uid'));
		$uid=array(session('uid'));
		
		if(isset($_GET['gid'])){
			$gid=$this->_get('gid', 'intval');
			$where['gid']=$gid;
			$uid=array();
		}
		

		$myJoin = M('baoming')->where(array('uid'=>session('uid')))->select();
		if($myJoin){
			foreach($myJoin as $k=>$v){
				$myJoin[$k]=$v['wid'];
			}
		}
		$where=array('id'=>array('IN',$myJoin));

		//p($myJoin);die;
		
		//分页
		$count=$db->where($where)->count();
		$page=new Page($count, 20);
		$limit=$page->firstRow.','.$page->listRows;
		
		
		$weibo=$db->getAll($where,$limit);
		//p($weibo);die;
		$this->page=$page->show();
		$this->weibo=$weibo;
		$this->isindex=1;
		//p($weibo);die;
		$this->display('index');
    }


    //我的关注
    public function myFollow(){
		
		$db=D('WeiboView');
		import('ORG.Util.Page');
		$where=array('fans'=>session('uid'));
		$uid=array(session('uid'));
		
		if(isset($_GET['gid'])){
			$gid=$this->_get('gid', 'intval');
			$where['gid']=$gid;
			$uid=array();
		}
		
		$result=M('follow')->where($where)->field('follow')->select();
		//p($result);die;
		//组合我和我关注的人的uid
		if($result){
			foreach($result as $k=>$v){
				$uid[]=$v['follow'];
			}
		}
		$where=array('uid'=>array('IN',$uid));
		
		//分页
		$count=$db->where($where)->count();
		$page=new Page($count, 20);
		$limit=$page->firstRow.','.$page->listRows;
		
		
		$weibo=$db->getAll($where,$limit);
		//p($weibo);die;
		$this->page=$page->show();
		$this->weibo=$weibo;
		$this->isindex=1;
		//p($weibo);die;
		$this->display('index');
    }

    //只看女生
    public function onlyGirl(){
		
		$db=D('WeiboView');
		import('ORG.Util.Page');
		$where=array('fans'=>session('uid'));
		$uid=array(session('uid'));
		
		if(isset($_GET['gid'])){
			$gid=$this->_get('gid', 'intval');
			$where['gid']=$gid;
			$uid=array();
		}
		
		$result=M('follow')->where($where)->field('follow')->select();
		//p($result);die;
		//组合我和我关注的人的uid
		if($result){
			foreach($result as $k=>$v){
				$uid[]=$v['follow'];
			}
		}

		$girl = M('userinfo')->where(array('sex'=>0))->field('uid')->select();

		
		foreach($girl as $k=>$v){
			$girl[$k] = $girl[$k]['uid'];
		}

		$where=array('uid'=>array('IN',$girl));
		
		//分页
		$count=$db->where($where)->count();
		$page=new Page($count, 20);
		$limit=$page->firstRow.','.$page->listRows;
		
		
		$weibo=$db->getAll($where,$limit);
		//p($weibo);die;
		$this->page=$page->show();
		$this->weibo=$weibo;
		$this->isindex=1;
		//p($weibo);die;
		$this->display('index');
    }

    //只看女生
    public function onlyBoy(){
		
		$db=D('WeiboView');
		import('ORG.Util.Page');
		$where=array('fans'=>session('uid'));
		$uid=array(session('uid'));
		
		if(isset($_GET['gid'])){
			$gid=$this->_get('gid', 'intval');
			$where['gid']=$gid;
			$uid=array();
		}
		
		$result=M('follow')->where($where)->field('follow')->select();
		//p($result);die;
		//组合我和我关注的人的uid
		if($result){
			foreach($result as $k=>$v){
				$uid[]=$v['follow'];
			}
		}

		$girl = M('userinfo')->where(array('sex'=>1))->field('uid')->select();

		
		foreach($girl as $k=>$v){
			$girl[$k] = $girl[$k]['uid'];
		}

		$where=array('uid'=>array('IN',$girl));
		
		//分页
		$count=$db->where($where)->count();
		$page=new Page($count, 20);
		$limit=$page->firstRow.','.$page->listRows;
		
		
		$weibo=$db->getAll($where,$limit);
		//p($weibo);die;
		$this->page=$page->show();
		$this->weibo=$weibo;
		$this->isindex=1;
		//p($weibo);die;
		$this->display('index');
    }
	
	//用户退出登录处理
	public function loginOut(){
		//删除session
		session_unset();
		session_destroy();
		//清除自动登录的cookie值
		@setcookie('auto','',time(),'/');
		redirect(U('Login/index'));
	}
	
	//微博发布
	public function sendWeibo(){
		if(!$this->isPost()){
			halt('页面不存在');
		}
		$weibo=array(
			'content'=>$this->_post('content'),
			'time'=>time(),
			'uid'=>session('uid'),
			
		);
		$wid=M('weibo')->add($weibo);

		$activity = array(
			'location' => $this->_post('location'),
			'activity_time' => $this->_post('activity_time'),
			'qq' => $this->_post('qq'),
			'telephone' => $this->_post('telephone'),
			'wid' => $wid
		);
		
		$a = M('activity');
		$a->add($activity);

		$this->_atmeHandel($weibo['content'],$wid);
		if($this->_post('mini')){
			$picture=array(
				'max'=>$this->_post('max'),
				'medium'=>$this->_post('medium'),
				'mini'=>$this->_post('mini'),
				'wid'=>$wid
			);
			M('picture')->add($picture);
		}
		M('userinfo')->where(array('uid'=>session('uid')))->setInc('weibo');
		$this->success('发布成功');
	}
	//转发微博处理
	public function turn(){
		if(!$this->isPost()){
			halt('页面不存在');
		}
		$id=$this->_post('wid','intval');
		$tid=$this->_post('tid','intval');
		$data=array(
			'content'=>$this->_post('content'),
			'isturn'=>$tid ? $tid : $id,
			'time'=>time(),
			'uid'=>session('uid')
		);
		$db=M('weibo');
		if($db->data($data)->add()){
			$db->where(array('id'=>$id))->setInc('turn');
			if($tid){
				$db->where(array('id'=>$tid))->setInc('turn');
			}
			M('userinfo')->where(array('uid'=>session('uid')))->setInc('weibo');
			if(isset($_POST['becomment'])){
				$data=array(
					'content'=>$this->_post('content'),
					'time'=>time(),
					'uid'=>session('uid')
				);
				if(M('comment')->data($data)->add()){
					$db->where(array('id'=>$id))->setInc('comment');
				}
			}
			$this->success('转发成功');
		}else{
			$this->error('转发失败');
		}
	}
	
	//atme处理
	private function _atmeHandel($content,$wid){
		$preg='/@(\S+?)\s/is';
		preg_match_all($preg,$content,$arr);
		
		if(!empty($arr[1])){
			$db=M('userinfo');
			$atme=M('atme');
			foreach($arr[1] as $v){
				$uid=$db->where(array('username'=>$v))->getField('uid');
				if($uid){
					$data=array(
						'uid'=>$uid,
						'wid'=>$wid
					);
					$atme->data($data)->add();
				}
			}
		}
	}
	
	//收藏微博
	public function keep(){
		if(!$this->isAjax()){
			halt('页面不存在');
		}
		$wid=$this->_post('wid','intval');
		$uid=session('uid');
		$db=M('keep');
		$where=array('wid'=>$wid,'uid'=>$uid);
		
		if($db->where($where)->getField('id')){
			echo -1;
			exit();
		}
		$data=array(
			'uid'=>$uid,
			'wid'=>$wid,
			'time'=>time()
		);
		
		if($db->data($data)->add()){
			M('weibo')->where(array('id'=>$wid))->setInc('keep');
			echo 1;
		}else{
			echo 0;
		}
	}
	
	
	//异步评论
	public function comment(){
		if(!$this->isAjax()){
			halt('页面不存在');
		}
		$data=array(
			'content'=>$this->_post('content'),
			'time'=>time(),
			'wid'=>$this->_post('wid'),
			'uid'=>session('uid')
		);
		
		if(M('comment')->add($data)){
			$field=array('username','face50'=>'face','uid');
			$where=array('uid'=>$data['uid']);
			$user=M('userinfo')->where($where)->field($field)->find();
			
			$db=M('weibo');
			$db->where(array('id'=>$data['wid']))->setInc('comment');
			if($_POST['isturn']){
				$field=array('id','content','isturn');
				$weibo=$db->field($field)->find($data['wid']);
				$content=$weibo['isturn'] ? $data['content'].' // @'.$user['username'].' : '.$weibo['content']:$data['content'];
				
				$cons=array(
					'content'=>$content,
					'isturn'=>$weibo['isturn'] ? $weibo['isturn']:$data['wid'],
					'time'=>$data['time'],
					'uid'=>$data['uid']
				);
				
				if($db->data($cons)->add()){
					$db->where(array('id'=>$weibo['id']))->setInc('turn');
				}
				echo 1;
				exit();
			}
			$str="<hr/>
				<div class='row'>
					<div class='col-md-1'>
						<a href='".U('/'.$data['uid'])."'><img src='";
			$str.=__ROOT__;
			if($user['face']){
				$str.="/Uploads/Face/".$user['face'];
			}else{
				$str.="/Public/Them/default/images/1.jpg";
			}
			$str.="' width='30px' height='30px'/></a>
					</div>
					<div class='col-md-11'>
						<a href='".U('/'.$data['uid'])."'>".$user['username']."</a>：".$data['content']."(".time_format($data['time']).")
					</div>
					<div class='row'>
						<div class='col-md-2 col-md-offset-10'>
							回复
						</div>
					</div>
				</div>";
			echo $str;
		}else{
			echo 0;
		}
	}
	
	//异步获取评论类容
	public function getComment(){
		if(!$this->isPost()){
			halt('页面不存在');
		}
		$wid=$this->_post('wid','intval');
		$where=array('wid'=>$wid);
		
		$count=M('comment')->where($where)->count();
		$total=ceil($count/10);
		$page=isset($_POST['page']) ? $this->_post('page','intval') : 1;
		$limit=$page<2 ? '0,10' : (10*($page-1)).',10';
		
		$result=D('CommentView')->where($where)->limit($limit)->order('time DESC')->select();
		if($result){
			$str='';
			foreach($result as $v){
				$str.="<hr/>
				<div class='row comment-list'>
					<div class='col-md-1'>
						<a href='".U('/'.$v['uid'])."'><img src='";
				$str.=__ROOT__;
				if($v['face']){
					$str.="/Uploads/Face/".$v['face'];
				}else{
					$str.="/Public/Them/default/images/1.jpg";
				}
				$str.="' width='30px' height='30px'/></a>
					</div>
					<div class='col-md-11'>
						<a href='".U('/'.$v['uid'])."'>".$v['username']."</a>：".replace_weibo($v['content'])."(".time_format($v['time']).")
					</div>
					<div class='row'>
						<div class='col-md-2 col-md-offset-10'>
							<span username='".$v['username']."' class='text-primary pointer huifu' target='".$wid."'>回复</span>
						</div>
					</div>
				</div>";
			}
			
			if($total>1){
				$str.='<p>';
				
				switch($page){
					case $page>1 && $page < $total:
						$str.='<a class="btn comment-page comment-list" page="' . ($page-1) . '" wid="'.$wid.'">上一页</a>';
						$str.='<a class="btn comment-page comment-list" page="' . ($page+1) . '" wid="'.$wid.'">下一页</a>';
						break;
					case $page < $total:
						$str.='<a class="btn comment-page comment-list" page="' . ($page+1) . '" wid="'.$wid.'">下一页</a>';
						break;
					case $page == $total:
						$str.='<a class="btn comment-page comment-list" page="' . ($page-1) . '" wid="'.$wid.'">上一页</a>';
						break;
				}
				
				$str.='</p>';
			}
			
			echo $str;
		}else{
			echo 0;
		}
	}
	
	//异步删除微博
	public function delWeibo(){
		if(!$this->isAjax()){
			halt('页面不存在...');
		}
		$wid=$this->_post('wid','intval');
		if(M('weibo')->delete($wid)){
			$db=M('picture');
			$img=$db->where(array('wid'=>$wid))->find();
			if($img){
				$db->delete($img['id']);
				@unlink('./Uploads/Pic/'.$img['mini']);
				@unlink('./Uploads/Pic/'.$img['medium']);
				@unlink('./Uploads/Pic/'.$img['max']);
			}
			echo 1;
		}else{
			echo 0;
		}
	}

	//创业寻求创业伙伴展示页面
	public function gonggao_cy(){
		$this->display();
	}

	//PHP学习求队友展示页面
	public function gonggao_php(){
		$this->display();
	}
}