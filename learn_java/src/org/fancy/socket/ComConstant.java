package org.fancy.socket;

public class ComConstant {

	public final static String SOCKET_ENCODE = "UTF-8";
	
	public final static int BUF_SIZE = 3 * 1024;
	
	public final static int PORT = 9080;
	
	public final static int NUM_PER_THREAD = 4; // 单个CPU时线程池中的工作线程个数
	
	private ComConstant(){}
}
