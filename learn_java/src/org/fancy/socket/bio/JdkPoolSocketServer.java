package org.fancy.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.fancy.socket.ComConstant;
import org.fancy.socket.bio.handler.XmlHandler;

/**
 * 使用JDK自带的线程池
 * @author yexiong
 */
public class JdkPoolSocketServer extends Thread {
	
	private Socket client ;
	
	private ServerSocket serverSocket ;
		
	private ExecutorService executorService ;// 线程池
	
	private boolean flag;
	
	public JdkPoolSocketServer() {
		try {
			serverSocket = new ServerSocket(ComConstant.PORT);
			int size = ComConstant.NUM_PER_THREAD * Runtime.getRuntime().availableProcessors();
			executorService = Executors.newFixedThreadPool(size);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (isFlag()) {
			try {
				client = serverSocket.accept();
				executorService.execute(new ReceiveThread(client, new XmlHandler()));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}	
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void stopServer() {
		this.setFlag(false);
		try {
			if(serverSocket != null && !serverSocket.isClosed())
				serverSocket.close();
			
			if(client != null && !client.isClosed())
				client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
