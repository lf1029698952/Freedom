<?php
	class SearchAction extends CommonAction{
		
		//搜索用户
		public function searchUser(){
			
			$keyword=$this->_post('keyword');
			if($keyword){
				$where=array(
					'username'=>array('LIKE','%'.$keyword.'%'),
					'uid'=>array('NEQ',session('uid'))
				);
				$field=array('username','sex','location','intro','face80','follow','fans','weibo','uid');
				$db=M('userinfo');
				import('ORG.Util.Page');
				$count=$db->where($where)->count('id');
				$page=new Page($count,20);
				$limit=$page->firstRow.','.$page->listRows;
				$result=$db->where($where)->field($field)->limit($limit)->select();
				$result=$this->_getMutual($result);
				$this->page=$page->show();
			}
			$this->result=$result ? $result : false;
			$this->keyword=$keyword;
			$this->display();
		}
		
		public function _getMutual($result){
			if(!$result)
				return false;
				
			$db=M('follow');
			
			foreach($result as $k=>$v){
				$sql='(select `follow` from `hd_follow` where `fans`='.session('uid').' and `follow`='.$v['uid'].')union(select `follow` from `hd_follow` where `fans`='.$v['uid'].' and `follow`='.session('uid').')';
				$mutual=$db->query($sql);
				if(count($mutual)==2){
					$result[$k]['mutual']=1;
					$result[$k]['followed']=1;
				}else{
					$result[$k]['mutual']=0;
					
					$where=array(
						'follow'=>$v['uid'],
						'fans'=>session('uid'),
					);
					$result[$k]['followed']=$db->where($where)->count();
				}
			}
			
			return $result;
			
		}
		
		//搜索微博
		public function searchWeibo(){
			
			$keyword=$this->_post('keyword');
			if($keyword){
				$where=array('content'=>array('LIKE','%'.$keyword.'%'));
				
				$db=D('WeiboView');
				
				import('ORG.Util.Page');
				$count=$db->where($where)->count('id');
				$page=new Page($count,20);
				$limit=$page->firstRow.','.$page->listRows;
				
				$weibo=$db->getAll($where,$limit);
				
				$this->weibo=$weibo;
				$this->page=$page->show();
				
			}
			$this->result=$result ? $result : false;
			$this->keyword=$keyword;
			$this->display();
		}
	}
?>