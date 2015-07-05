<?php
return array(
	//'配置项'=>'配置值'
	'DB_HOST'=>'localhost',
	'DB_USER'=>'root',
	'DB_PWD'=>'sbmtd1993',
	'DB_NAME'=>'weibo',
	'DB_PREFIX'=>'hd_',
	'DEFAULT_THEME'=>'default',
	'UPLOAD_MAX_SIZE' => 2000000,	//最大上传大小
	'UPLOAD_PATH' => './Uploads/',	//文件上传保存路径
	'UPLOAD_EXTS' => array('jpg', 'jpeg', 'gif', 'png'),	//允许上传文件的后缀
	
	//URL路由配置
	'URL_ROUTER_ON' => true,
	'URL_ROUTE_RULES'=> array(
		':id\d'=>'User/index',
		'follow/:uid\d'=>array('User/followList','type=1'),
		'fans/:uid\d'=>array('User/followList','type=0')
	),
);
?>