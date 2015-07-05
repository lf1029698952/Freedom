<?php
	class TestAction extends Action{
		public function index(){

			$imgName = date('Y_m').'/'.uniqid().'jpg';
			echo (__ROOT__.'/Uploads/Face/'.$imgName);
		}
	}
?>