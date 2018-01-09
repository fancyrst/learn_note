package org.fancy.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.fancy.socket.ComConstant;
import org.fancy.socket.bio.handler.XmlHandler;

/**
 * 多线程实现Socket服务端
 * @author yexiong
 */
public class DefaultSocketServer extends Thread {

	private ServerSocket serverSocket = null;
	
	private Socket socket = null;
	
	private boolean flag;
	
	private ReceiveThread receiveThread = null;
	
	public DefaultSocketServer() {
		//默认最大队列长度被设置为 50。如果队列满时收到连接指示，则拒绝该连接。
		try {
			serverSocket = new ServerSocket(ComConstant.PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {			
			while(this.isFlag()) {
				socket = serverSocket.accept();
				receiveThread = new ReceiveThread(socket,new XmlHandler());
				receiveThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			
			if(socket != null && !socket.isClosed())
				socket.close();
			if(receiveThread != null)
				receiveThread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
