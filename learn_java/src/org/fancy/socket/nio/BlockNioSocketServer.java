package org.fancy.socket.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fancy.socket.ComConstant;
import org.fancy.socket.nio.handler.BlockServerHandler;

/**
 * 使用NIO的ServerSocketChannel创建阻塞的Socket服务端
 * 使用JDK自带的线程池ExecutorService，多线程处理客户端请求
 * @author yexiong
 */
public class BlockNioSocketServer extends Thread {

	private ServerSocketChannel ssc;
	
	private ExecutorService executorService; //JDK自带线程池
	
	public BlockNioSocketServer() {
		
		try {
			int size = Runtime.getRuntime().availableProcessors() * ComConstant.NUM_PER_THREAD;
			executorService = Executors.newFixedThreadPool(size);
			
			// 默认是阻塞模式的，通过configureBlocking(false)设置为非阻塞模式
			ssc = ServerSocketChannel.open();
			ssc.socket().setReuseAddress(true);	
			ssc.socket().bind(new InetSocketAddress(ComConstant.PORT));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while (true) {
			SocketChannel sc = null;
			try {
				sc = ssc.accept();
				executorService.execute(new BlockServerHandler(sc));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
