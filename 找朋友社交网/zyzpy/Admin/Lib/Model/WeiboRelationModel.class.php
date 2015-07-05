<?php
/**
 * 微博所有相关数据关联模型
 */
Class WeiboRelationModel extends RelationModel {

	Protected $tableName = 'weibo';

	Protected $_link = array(
		'picture' => array(
			'mapping_type' => HAS_ONE,
			'foreign_key' => 'wid'
			),
		'comment' => array(
			'mapping_type' => HAS_MANY,
			'foreign_key' => 'wid'
			),
		'keep' => array(
			'mapping_type' => HAS_MANY,
			'foreign_key' => 'wid'
			),
		'atme' => array(
			'mapping_type' => HAS_MANY,
			'foreign_key' => 'wid'
			)
		);
}
?>