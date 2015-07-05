<?php
	//注册与登录控制器
	Class LoginAction extends Action{
		//登录
		public function index(){
			$this->display();
		}
		
		//登录表单处理
		public function login(){
			if(!$this->isPost()){
				halt('页面不存在');
			}
			$account=$this->_post('username');
			$password=$this->_post('password','md5');
			$where=array('account'=>$account);
			$user=M('user')->where($where)->find();
			
			if(!$user || $user['password']!=$password){
				$this->error("用户名或者密码错误！~_~");
			}
			
			//处理下一次自动登录
			if(isset($_POST['auto'])){
				$account=$user['account'];
				$password=$user['password'];
				$value=$account.'|'.$password;
				@setcookie('auto', encryption($value), (time()+3600), '/');
			}
			
			session('uid',$user['id']);
			redirect(__APP__);
		}
		
		//注册页面
		public function register(){
			$this->display();
		}
		
		//注册表单处理
		public function runRegister(){
			if(!$this->isPost()){
				halt('页面不存在');
			}
			if(md5($_POST['verify'])!=$_SESSION['verify']){
				$this->error('验证码错误');
			}
			
			if($_POST['password']!=$_POST['repassword']){
				$this->error('两次密码不一致');
			}
			//提取表单数据
			$data=array(
				'account'=>$this->_post('account'),
				'password'=>$this->_post('password','md5'),
				'registime'=>time(),
				'userinfo'=>array(
					'username'=>$this->_post('username'),
					'sex'=>$this->_post('sex')
				)
			);
			$id=D('UserRelation')->relation(true)->add($data);
			if($id){
				session('uid',$id);
				if($data['userinfo']['sex'] == 0){
					$style=array(
						'style'=>'style2'
					);
					M('userinfo')->where(array('uid'=>session('uid')))->save($style);
				}
				header('Content-Type:text/html;Charset=UTF-8');
				redirect(__APP__, 3, '注册成功，正在为您跳转...');
			}else {
				$this->error('注册失败，请重试...');
			}
		}
		//验证码
		public function verify(){
			import('ORG.Util.Image');
			Image::buildImageVerify(1, 1, 'png');
		}
	}
?>