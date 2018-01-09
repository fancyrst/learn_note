package org.fancy.nio.rw;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
	
	public static void main(String[] args) throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			String inName = "E:/log/ept_error.log";
			String outName = "E:/temp";
			
			fis = new FileInputStream(inName);
			fos = new FileOutputStream(outName,true);
			
			FileChannel fcIs = fis.getChannel();
			FileChannel fcOs = fos.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (true) { // 读->写 循环，直至文件copy完毕
				buffer.clear(); // 清除缓冲区，为读取准备
				int read = fcIs.read(buffer);//读
				
				if (read == -1)
					break;
				
				buffer.flip();//读完毕，准备写
				
				fcOs.write(buffer);
				
			}
			fcIs.close();
			fcOs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (fis != null) fis.close();
			if (fos != null) fos.close();
		}
	}
}
