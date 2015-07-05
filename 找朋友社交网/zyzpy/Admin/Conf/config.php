<?php
return array(

	//数据库配置
	'DB_HOST' => '127.0.0.1',	//数据库服务器地址
	'DB_USER' => 'root',	//数据库连接用户名
	'DB_PWD' => '',	//数据库连接密码
	'DB_NAME' => 'weibo', //使用数据库名称
	'DB_PREFIX' => 'hd_',	//数据库表前缀

	//自定义模版潜换
	'TMPL_PARSE_STRING' => array(
		'__PUBLIC__' => __ROOT__ . '/Admin/Tpl/Public',
		),
);
?>