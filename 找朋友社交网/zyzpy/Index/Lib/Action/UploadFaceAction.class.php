<?php
	//头像上传控制器
	class UploadFaceAction extends Action{
		//头像上传处理
		Public function uploadFace () {
			if (!$this->isPost()) {
				halt('页面不存在');
			}
			$upload = $this->_upload('Face', '180,80,50', '180,80,50');
			echo json_encode($upload);
		}
		
		//图片上传处理
		Public function uploadPic () {
			if (!$this->isPost()) {
				halt('页面不存在');
			}
			$upload = $this->_upload('Pic', '800,380,150', '800,380,150');
			echo json_encode($upload);
		}
		
		/**
		 * 图片上传处理
		 * @param  [String] $path   [保存文件夹名称]
		 * @param  [String] $width  [缩略图宽度多个用，号分隔]
		 * @param  [String] $height [缩略图高度多个用，号分隔(要与宽度一一对应)]
		 * @return [Array]         [图片上传信息]
		 */
		Private function _upload ($path, $width, $height) {
			import('ORG.Net.UploadFile');	//引入ThinkPHP文件上传类
			$obj = new UploadFile();	//实例化上传类
			$obj->maxSize = C('UPLOAD_MAX_SIZE');	//图片最大上传大小
			$obj->savePath = C('UPLOAD_PATH') . $path . '/';	//图片保存路径
			$obj->saveRule = 'uniqid';	//保存文件名
			$obj->uploadReplace = true;	//覆盖同名文件
			$obj->allowExts = C('UPLOAD_EXTS');	//允许上传文件的后缀名
			$obj->thumb = true;	//生成缩略图
			$obj->thumbMaxWidth = $width;	//缩略图宽度
			$obj->thumbMaxHeight = $height;	//缩略图高度
			$obj->thumbPrefix = 'max_,medium_,mini_';	//缩略图后缀名
			$obj->thumbPath = $obj->savePath . date('Y_m') . '/';	//缩略图保存图径
			$obj->thumbRemoveOrigin = true;	//删除原图
			$obj->autoSub = true;	//使用子目录保存文件
			$obj->subType = 'date';	//使用日期为子目录名称
			$obj->dateFormat = 'Y_m';	//使用 年_月 形式

			if (!$obj->upload()) {
				return array('status' => 0, 'msg' => $obj->getErrorMsg());
			} else {
				$info = $obj->getUploadFileInfo();
				$pic=explode('/',$info[0]['savename']);
				return array(
					'status'=>1,
					'path'=>array(
						'max'=>$pic[0].'/max_'.$pic[1],
						'medium'=>$pic[0].'/medium_'.$pic[1],
						'mini'=>$pic[0].'/mini_'.$pic[1]
					)
				);
			}
		}
	}
?>