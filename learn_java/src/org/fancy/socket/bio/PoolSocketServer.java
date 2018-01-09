package org.fancy.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.fancy.socket.ComConstant;
import org.fancy.socket.bio.handler.XmlHandler;
import org.fancy.socket.bio.pool.ThreadPool;

/**
 * 自定义线程池实现Server Socket
 * @author Administrator
 *
 */
public class PoolSocketServer extends Thread {
	
	private ServerSocket serverSocket;

	private ThreadPool threadPool; //线程池
	
	private boolean isFlag;
	
	private Socket client;
	
	public PoolSocketServer() {
		try {
			serverSocket = new ServerSocket(ComConstant.PORT);
			threadPool = new ThreadPool(Runtime.getRuntime().availableProcessors() * ComConstant.NUM_PER_THREAD);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			
			while (isFlag) {
				Socket client = serverSocket.accept();
				ReceiveThread receiveThread = new ReceiveThread(client, new XmlHandler());
				threadPool.execute(receiveThread);			
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isFlag() {
		return isFlag;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
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
