<?php
/**
 * 公共控制器
 */
Class CommonAction extends Action {

	/**
	 * 判断用户是否已登录
	 */
	Public function _initialize () {
		if (!isset($_SESSION['uid']) || !isset($_SESSION['username'])) {
			redirect(U('Login/index'));
		}
	}
}
?>