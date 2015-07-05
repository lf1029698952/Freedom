<?php
/**
 * 后台登录控制器
 */
Class LoginAction extends Action {

	/**
	 * 登录页面视图
	 */
	Public function index () {
		$this->display();
	}

	/**
	 * 登录操作处理
	 */
	Public function login () {
		if (!$this->isPost()) {
			halt('页面不存在');
		}

		if (!isset($_POST['submit'])) {
			return false;
		}

		//验证码对比
		if ($_SESSION['verify'] != md5($_POST['verify'])) {
			$this->error('验证码错误');
		}

		$name = $this->_post('uname');
		$pwd = $this->_post('pwd', 'md5');
		$db = M('admin');
		$user = $db->where(array('username' => $name))->find();

		if (!user || $user['password'] != $pwd) {
			$this->error('账号或密码错误');
		}

		if ($user['lock']) {
			$this->error('账号被锁定');
		}

		$data = array(
			'id' => $user['id'],
			'logintime' => time(),
			'loginip' => get_client_ip()
			);
		$db->save($data);

		session('uid', $user['id']);
		session('username', $user['username']);
		session('logintime', date('Y-m-d H:i', $user['logintime']));
		session('now', date('Y-m-d H:i', time()));
		session('loginip', $user['loginip']);
		session('admin', $user['admin']);
		$this->success('正在登录...', __APP__);
	}

	/**
	 * 验证码
	 */
	Public function verify () {
		import('ORG.Util.Image');
		Image::buildImageVerify();
	}
}
?>