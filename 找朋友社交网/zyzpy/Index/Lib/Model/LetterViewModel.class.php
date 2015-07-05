<?php
	//私信视图模型
	class LetterViewModel extends ViewModel{
	
		Protected $viewFields=array(
			'letter'=>array(
				'content','id','time',
				'_type'=>'LEFT'
			),
			'userinfo'=>array(
				'face50'=>'face','username','uid',
				'_on'=>'letter.from=userinfo.uid'
			)
		);
	}
?>