<?php
// 本类由系统自动生成，仅供测试用途
class ActivityAction extends CommonAction {
	//主页面视图
    public function index(){
		
		$db=D('ActivityView');
		$wid = $_GET['wid'];
		$where=array('id'=>$wid);
		
		$activity = $db->where($where)->select();

		//p($activity);die;
        $where = array('wid'=>$wid);

        $baoming = D('BaomingView')->where($where)->select();
        //p($baoming);die;
        $this->baoming = $baoming;
        $this->activity=$activity;
        //p($activity);die;
		$this->display();
    }

    //异步报名，取消报名
    public function baoming(){
    	if(!$this->isAjax()){
    		halt('不给你看');
    	}

    	$data = array(
    		'wid'=>$this->_post('wid'),
    		'uid'=>$_SESSION['uid']
    	);
    	$result = M('baoming')->where($data)->select();
    	//查看是否已经报名，如果报名了就返回0
    	if($result[0]){
    		echo -1;
    		die;
    	}

		if(M('baoming')->add($data)){
    		echo 1;
    	}else{
    		echo 0;
    	}
    }

    //异步取消报名
    public function delBaoming(){
    	if(!$this->isAjax()){
    		halt('不给你看');
    	}

    	$data = array(
    		'wid'=>$this->_post('wid'),
    		'uid'=>$_SESSION['uid']
    	);
		if(M('baoming')->where($data)->delete()){
    		echo 1;
    	}else{
    		echo 0;
    	}
    }

    //异步录取
    public function luqu(){
    	if(!$this->isAjax()){
    		halt('不给你看');
    	}

    	$wid = $this->_post('wid');
    	$uid = $this->_post('uid');

    	$data = array(
    		'wid'=>$wid,
    		'uid'=>$uid
    	);
    	
        if(M('baoming')->where($data)->setInc('is_luqu')){
            echo 1;
        }else{
            echo 0;
        }
    }

     //异步开除
    public function kaichu(){
        if(!$this->isAjax()){
            halt('不给你看');
        }

        $wid = $this->_post('wid');
        $uid = $this->_post('uid');

        $data = array(
            'wid'=>$wid,
            'uid'=>$uid
        );
        
        if(M('baoming')->where($data)->setDec('is_luqu')){
            echo 1;
        }else{
            echo 0;
        }
    }
}