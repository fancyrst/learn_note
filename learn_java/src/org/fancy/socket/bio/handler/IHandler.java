package org.fancy.socket.bio.handler;

import java.net.Socket;

public interface IHandler {

	public void init(Socket client);
	
	public void process();
	
	public void destory();
}
