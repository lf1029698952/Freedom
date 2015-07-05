<?php
	//评论表与用户表视图模型
	class CommentViewModel extends ViewModel{
		Protected $viewFields=array(
			'comment'=>array(
				'id','content','time','wid',
				'_type'=>'LEFT'
			),
			'userinfo'=>array(
				'username','face50'=>'face','uid',
				'_on'=>'comment.uid=userinfo.uid'
			)
		);
	}
?>