<?php
	//报名视图模型
	class BaomingViewModel extends ViewModel{
		protected $viewFields = array(
				'baoming'=>array(
					'uid','wid','is_luqu',
					'_type'=>'LEFT'
				),
				'userinfo'=>array(
					'username','face50'=>'face','uid',
					'_on'=>'baoming.uid = userinfo.uid'
				),
			);
	}
?>