<?php
/**
 *  微博管理控制器
 */
Class WeiboAction extends CommonAction {

	/**
	 *  原作微博列表
	 * @return [type] [description]
	 */
	Public function index () {
		import('ORG.Util.Page');
		$where = array('isturn' => 0);
		$count = M('weibo')->where($where)->count();
		$page = new Page($count, 20);
		$limit = $page->firstRow . ',' . $page->listRows;

		$weibo = D('WeiboView')->where($where)->limit($limit)->order('time DESC')->select();
		$this->weibo = $weibo;
		$this->page = $page->show();
		$this->display();
	}

	/**
	 * 删除微博
	 */
	Public function delWeibo () {
		$id = $this->_get('id', 'intval');
		$uid = $this->_get('uid', 'intval');

		//删除微博
		if (D('WeiboRelation')->relation(true)->delete($id)) {
			//用户发布微博数-1
			M('userinfo')->where(array('uid' => $uid))->setDec('weibo');
			$this->success('删除成功', U('index'));
		} else {
			$this->error('删除失败，请重试...');
		}
	}

	/**
	 * 转发微博列表
	 */
	Public function turn () {
		import('ORG.Util.Page');
		$where = array('isturn' => array('GT', 0));
		$count = M('weibo')->where($where)->count();
		$page = new Page($count, 20);
		$limit = $page->firstRow . ',' . $page->listRows;

		$db = D('WeiboView');
		unset($db->viewFields['picture']);
		$turn = $db->where($where)->limit($limit)->order('time DESC')->select();

		$this->turn = $turn;
		$this->page = $page->show();
		$this->display();
	}

	/**
	 * 微博检索
	 */
	Public function sechWeibo () {
		if (isset($_GET['sech'])) {
			$where = array('content' => array('LIKE', '%' . $this->_get('sech') . '%'));
			$weibo = D('WeiboView')->where($where)->order('time DESC')->select();
			
			$this->weibo = $weibo ? $weibo : false;
		}
		$this->display();
	}

	/**
	 * 评论列表
	 */
	Public function comment () {
		import('ORG.Util.Page');
		$count = M('comment')->count();
		$page = new Page($count, 20);
		$limit = $page->firstRow . ',' . $page->listRows;

		$comment = D('CommentView')->limit($limit)->order('time DESC')->select();
		$this->comment = $comment;
		$this->page = $page->show();
		$this->display();
	}

	/**
	 * 删除评论
	 */
	Public function delComment () {
		$id = $this->_get('id', 'intval');
		$wid = $this->_get('wid', 'intval');

		if (M('comment')->delete($id)) {
			M('weibo')->where(array('id' => $wid))->setDec('comment');
			$this->success('删除成功', $_SERVER['HTTP_REFERER']);
		} else {
			$this->error('删除失败，请重试...');
		}
	}

	/**
	 * 评论检索
	 */
	Public function sechComment () {
		if (isset($_GET['sech'])) {
			$where = array('content' => array('LIKE', '%' . $this->_get('sech') . '%'));
			$comment = D('CommentView')->where($where)->order('time DESC')->select();
			$this->comment = $comment ? $comment : false;
		}
		$this->display();
	}
}
?>