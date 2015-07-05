<?php
	//公共控制器，验证用户是否登录，自动登录
	class CommonAction extends Action{
		public function _initialize(){
			//自动登录处理
			if(isset($_COOKIE['auto']) && !isset($_SESSION['uid'])){
				$value=$_COOKIE['auto'];//获得客户端的密码
				$value=encryption($value);//解密函数，解密放在客户端的$_COOKIE['auto']
				$value=explode('|',$value);//以|为拆分点拆分用户名和密码的组合
				$where=array('account'=>$value[0]);
				$user=M('user')->where($where)->find();
				$password=substr($user['password'],1,5);//因为解密后，32位密码部分丢失，所以截取前5个比较
				$value[1]=substr($value[1],1,5);
				
				if($user && $value[1]==$password){//比较拆分后的客户端密码和服务器密码
					session('uid',$user['id']);
				}
			}
			
			if(!isset($_SESSION['uid'])){
				redirect(U('Login/index'));
			}
		}
		
		//异步创建分组
		public function addGroup(){
			$data=array(
				'name'=>$this->_post('name'),
				'uid'=>session('uid')
			);
			if(M('group')->data($data)->add()){
				echo json_encode(array('status'=>1,'msg'=>'创建成功'));
			}else{
				echo json_encode(array('status'=>0,'msg'=>'创建失败请重试。。。'));
			}
		}
		
		//异步添加关注
		public function addFollow(){
			if(!$this->isAjax()){
				halt('页面不在');
			}
			$data=array(
				'follow'=>$this->_post('follow','intval'),
				'fans'=>(int) session('uid'),
				'gid'=>$this->_post('gid','intval')
			);
			if(M('follow')->data($data)->add()){
				$db=M('userinfo');
				$db->where(array('uid'=>$data['follow']))->setInc('fans');
				$db->where(array('uid'=>session('uid')))->setInc('follow');
				echo json_encode(array('status'=>1,'msg'=>'关注成功'));
			}else{
				echo json_encode(array('status'=>0,'msg'=>'关注失败请重试...'));
			}
		}
		
		//异步移除关注与粉丝
		public function delFollow(){
			if(!$this->isAjax()){
				halt('页面不在');
			}
			$uid=$this->_post('uid','intval');
			$type=$this->_post('type','intval');
			
			$where=$type ? array('follow'=>$uid,'fans'=>session('uid')) : array('fans'=>$uid,'follow'=>session('uid'));
			
			if(M('follow')->where($where)->delete()){
				$db=M('userinfo');
				
				if($type){
					$db->where(array('uid'=>session('uid')))->setDec('follow');
					$db->where(array('uid'=>$uid))->setDec('fans');
				}else{
					$db->where(array('uid'=>session('uid')))->setDec('fans');
					$db->where(array('uid'=>$uid))->setDec('follow');
				}
				echo 1;
			}else{
				echo 0;
			}
		}
		
		//异步修改模版风格
		public function editStyle(){
			if(!$this->isAjax()){
				halt('页面不在');
			}
			$style=$this->_post('style');
			if(M('userinfo')->where(array('uid'=>session('uid')))->save(array('style'=>$style))){
				echo 1;
			}else{
				echo 0;
			}
		}
	}
?>