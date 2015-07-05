<?php
	
	Class UserRelationModel extends RelationModel{
		
		Protected $tableName='user';
		
		Protected $_link=array(
			'userinfo'=>array(
				'mapping_type'=>HAS_ONE,
				'foreign_key'=>'uid'
			)
		);
		
	}
?>