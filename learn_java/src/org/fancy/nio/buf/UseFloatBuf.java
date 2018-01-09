package org.fancy.nio.buf;

import java.nio.FloatBuffer;

public class UseFloatBuf {
	public static void main(String[] args) {
		//分配新的 float 缓冲区
		FloatBuffer fb = FloatBuffer.allocate(10);
		for (int i= 0; i<fb.capacity(); i++) {
			float f = (float)(i * Math.PI);
			fb.put(f);
		}
		//fb.put(33.3f);//容量是10,目前已满。不会自动增加缓冲区
		// 在一系列通道读取或放置 操作之后，调用此方法为一系列通道写入或相对获取 操作做好准备。
		fb.flip();
		// 调用flip()方法后，准备数据写入通道。此时不能往容器放置数据
		//fb.put(43.3f);
		// 告知在当前位置和限制之间是否有元素。
		while (fb.hasRemaining()) {
			float f = fb.get();
			System.out.println(f);
		}
	}
}
