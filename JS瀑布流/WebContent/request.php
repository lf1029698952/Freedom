<?php
	header("Content-type:text/html; charset=utf-8");
	
	$mysql = mysql_connect("localhost", "root", "sbmtd193");
	mysql_query("set names utf8", $mysql);
	mysql_select_db("waterFall", $mysql);
	$rows = mysql_query("select * from demo limit 0, 10");
	$data = array();
	while ($row = mysql_fetch_assoc($rows))
	{
		$data[] = $row;
	} 
	
	echo 1;
?>