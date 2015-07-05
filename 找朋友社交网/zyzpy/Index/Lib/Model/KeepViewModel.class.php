<?php
	//收藏列表视图模型
	class KeepViewModel extends ViewModel{
		Protected $viewFields =  array(
			'keep'=>array(
				'id'=>'kid','time'=>'ktime',
				'_type'=>'INNER'
			),
			'weibo'=>array(
				'id', 'content', 'isturn', 'time', 'turn', 'comment','uid',
				'_on'=>'keep.wid=weibo.id',
				'_type'=>'LEFT'
			),
			'picture'=>array(
				'mini','medium','max',
				'_on'=>'weibo.id=picture.wid',
				'_type'=>'LEFT'
			),
			'userinfo'=>array(
				'username','face50'=>'face',
				'_on'=>'weibo.uid=userinfo.uid'
			)
		);
		
		public function getAll($where,$limit){
			$result=$this->where($where)->order('keep.time DESC')->limit($limit)->select();
			
			$db=D('WeiboView');
			foreach($result as $k=>$v){
				if($v['isturn']>0){
					$result[$k]['isturn']=$db->find($v['isturn']);
				}
			}
			
			return $result;
		}
	}
?>