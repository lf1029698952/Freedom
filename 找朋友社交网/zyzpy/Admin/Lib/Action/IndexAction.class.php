<?php
/**
 * 后盾首页控制器
 */
Class IndexAction extends CommonAction {

	/**
	 * 后台首页视图
	 */
	Public function index () {
		$this->display();
	}

	/**
	 * 后台信息页
	 */
	Public function copy () {
		$db = M('user');
		$this->user = $db->count();
		$this->lock = $db->where(array('lock' => 1))->count();

		$db = M('weibo');
		$this->weibo = $db->where(array('isturn' => 0))->count();
		$this->turn = $db->where(array('isturn' => array('GT', 0)))->count();
		$this->comment = M('comment')->count();
		$this->display();
	}

	/**
	 * 退出登录
	 */
	Public function loginOut () {
		session_unset();
		session_destroy();
		redirect(U('Login/index'));
	}
}
?>