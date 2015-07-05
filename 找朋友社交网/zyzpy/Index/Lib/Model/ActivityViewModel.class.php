<?php
	class ActivityViewModel extends ViewModel{
		
		Protected $viewFields=array(
			'weibo'=>array(
				'id','content','isturn','time','turn','keep','comment','uid',
				'_type'=>'LEFT'
			),
			'userinfo'=>array(
				'username','face50'=>'face',
				'_on'=>'weibo.uid=userinfo.uid',
				'_type'=>'LEFT'
			),
			'picture'=>array(
				'medium',
				'_on'=>'weibo.id=picture.wid'
			),
			'activity'=>array(
				'location','activity_time','qq','telephone',
				'_on'=>'weibo.id = activity.wid' 
			)
		);
	}
?>