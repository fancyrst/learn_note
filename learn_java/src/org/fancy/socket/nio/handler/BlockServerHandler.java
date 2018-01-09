package org.fancy.socket.nio.handler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import org.fancy.socket.ComConstant;

/**
 * 阻塞IO处理,每一个处理句柄起一个线程
 * @author yexiong
 *
 */
public class BlockServerHandler extends Thread {

	private SocketChannel socketChannel ;
	
	private ByteArrayOutputStream bos;
	
	private BufferedOutputStream os;
	
	private String receMsg;
	
	private String rtnMsg;
	
	
	public BlockServerHandler(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}
	
	@Override
	public void run() {
		
		try {
			
			Socket client = socketChannel.socket();
			InputStream is = client.getInputStream();
			bos = new ByteArrayOutputStream();	
			os = new BufferedOutputStream(client.getOutputStream());
			
			byte temps[] = new byte[ComConstant.BUF_SIZE];
			
			int len = 0;
			while((len = is.read(temps)) != -1) {
				bos.write(temps, 0, len);
			}
		
			receMsg = new String(bos.toByteArray(), ComConstant.SOCKET_ENCODE);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void send() throws Exception {
		if (this.rtnMsg != null) {
			os.write(rtnMsg.getBytes(ComConstant.SOCKET_ENCODE));
			os.flush();
		}
	}
	
	public void destory() {
		try {
			if (bos != null) 
				bos.close();
			if (os != null)
				os.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
