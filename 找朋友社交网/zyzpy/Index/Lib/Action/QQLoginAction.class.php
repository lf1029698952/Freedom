<?php
	//注册与登录控制器
	Class QQLoginAction extends Action{
		//登录
		public function index(){

			$username = $this->_post('username');
			$face = $this->_post('face');
			$openid = $this->_post('openid');
			$accesstoken = $this->_post('accesstoken');

			$user = M('user')->where(array('qq_login'=>$openid))->select();
			//p($user);die;

			if($this->_post('gender') == "男"){
				$sex = 1;
			}else{
				$sex = 0;
			}

			if($user[0]['id']){
				session('uid',$user[0]['id']);
				echo 1;
			}else{
				$data = array(
					'registime' => time(),
					'qq_login' => $openid
				);
				/*$uid = M('user');
				$uid->add($data);
				echo $uid->getLastSql();*/
				if($uid = M('user')->add($data)){
					$userinfo = array(
						'username' => $username,
						'sex' => $sex,
						'uid' => $uid
					);
					if(M('userinfo')->add($userinfo)){
						session('uid',$uid);

						if($sex == 0){
							$style=array(
								'style'=>'style2'
							);
							M('userinfo')->where(array('uid'=>session('uid')))->save($style);
						}

						echo 1;
						die;
						//处理头像
						/*$imgName = date('Y_m').'/'.uniqid().'.jpg';
						$img = file_get_contents($face);
						file_put_contents('Uploads/Face/'.$imgName,$img);
						$imgAll = array(
							'face50'=>$imgName,
							'face80'=>$imgName,
							'face180'=>$imgName
						);
						$where=array('uid'=>session('uid'));
						M('userinfo')->where($where)->save($imgAll);*/

					}else{
						echo 0;
					}
				}else{
					echo 0;
				}
			}
		}
		
	}
?>