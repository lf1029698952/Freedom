<?php
/**
 * 评论视图模型
 */
Class CommentViewModel extends ViewModel {

	Protected $viewFields = array(
		'comment' => array(
			'id', 'content', 'time', 'wid',
			'_type' => 'LEFT'
			),
		'userinfo' => array(
			'username', '_on' => 'comment.uid = userinfo.uid'
			)
		);
}
?>