<?php
	//格式化打印数组
	function p($arr){
		echo '<pre>';
		print_r($arr);
		echo '</pre>';
	}
	
	//加密函数与解密函数
	function encryption($value,$type=0){
		$key=md5('zyzpy');
		
		if(!$type){
			return $key ^ $value;
		}
		
		return $key ^ $value;
	}
	
	//将时间格式化后输出
	function time_format($time){
		$now=time();
		$today=strtotime(date('y-m-d',$now));
		$diff=$now-$time;
		$str='';
		switch($time){
			case $diff<60:
				$str=$diff.'秒前';
				break;
			case $diff<3600:
				$str=floor($diff/60).'分钟前';
				break;
			case $diff<3600*8:
				$str=floor($diff/(60*60)).'小时前';
				break;
			case $diff>$today:
				$str=(int)($diff/(60*60)).'小时' .$diff+($diff/60) % 60 . '分钟前';
				break;
			default:
				$str=date('Y-m-d H:i',$time);
		}
		return $str;
	}
	
	//替换微博路由，@用户，和表情
	
	function replace_weibo($content){
		//给URL地址加上<a>链接
		$preg='/(http:\/\/)?(www|bbs).(\w+).(com|cn|org|tv|cc)(\/\w*)*(\?\w+=\w+)*(\&\w+=\w+)*/is';
		$content=preg_replace($preg,'<a href="http://\\0">\\0</a>',$content);
		
		//给@用户加上<a>链接
		$preg='/@(\S+)\s/is';
		$content=preg_replace($preg,'<a target="_blank" href="' .__APP__ .'/User/\\1">@\\1</a> ',$content);
		
		$phiz= require './Public/Data/biaoqing.php';
		$preg='/\[(\S+?)\]/is';
		preg_match_all($preg,$content,$arr);
		if(!empty($arr[1])){
			foreach($arr[1] as $k=>$v){
				$name=array_search($v,$phiz);
				if($name){
					$content=str_replace($arr[0][$k],'<img src="' . __ROOT__ . '/Public/biaoqing/'.$name.'.gif" title="'.$v.'"/>',$content);
				}
			}
		}
		return $content;
	}
?>