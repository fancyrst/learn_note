package org.fancy.socket.bio;

import java.net.Socket;

import org.fancy.socket.bio.handler.IHandler;

/**
 * 处理具体socket的线程
 * @author yexiong
 *
 */
public class ReceiveThread extends Thread {

	private Socket client;
	
	private IHandler handler;	
	
	public ReceiveThread(Socket client, IHandler handler) {
		this.client = client;
		this.handler = handler;
	}
	
	
	@Override
	public void run() {
		try {
			this.handler.init(client);
			this.handler.process();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.handler.destory();
		}
	}
}
