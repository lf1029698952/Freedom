package cn.edu.swu.zhkp.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件处理工具类
 * 
 * @author yuhuaren
 *
 */
public class FileUtility {
	/**
	 * 保存上传的文件
	 * 
	 * @param in
	 *            输入流
	 * @param path
	 *            保存路径
	 * @throws IOException
	 */
	public static void saveUploadFile(InputStream in, String path)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		byte[] buffer = new byte[1024 * 1024];
		int byteread = 0;
		BufferedInputStream bis = new BufferedInputStream(in);
		while ((byteread = bis.read(buffer)) != -1) {
			bos.write(buffer, 0, byteread);
			bos.flush();
		}
		bos.close();
		fos.close();
		bis.close();
		in.close();
	}
}
