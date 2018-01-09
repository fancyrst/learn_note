package org.fancy.socket.bio.handler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.fancy.socket.ComConstant;


public abstract class AbstractHandler implements IHandler {
	/** 客户端 **/
	private Socket client = null;

	private InputStream is = null;
	
	private ByteArrayOutputStream bos = null;
	
	private BufferedOutputStream os = null;
	/** 接收到来自客户端的数据 **/
	private String receMsg = null;
	/** 回文数据  **/
	private String rtnMsg = null;
	
	@Override
	public void init(Socket client)
	{
		this.client = client;
	}
	
	public void process() {
		try {
			//1.接收来自客户端的数据
			receive();
			//2.解析数据，处理业务逻辑
			if (this.receMsg != null) {
				rtnMsg = handleMessage(receMsg);				
			}
			//3.回送数据至客户端
			send();
		} catch (Exception e) {
			// 异常统一在此处理，不再往上抛
		}
		
	}
	
	protected void receive() throws Exception {
		try {
			byte temps[] = null;				
			if (client.isConnected()) {
				is = client.getInputStream();
				bos = new ByteArrayOutputStream();	
				os = new BufferedOutputStream(client.getOutputStream());
				temps = new byte[ComConstant.BUF_SIZE];
			/** 读取Socket数据应用ByteArrayOutputStream，而不是BufferdInputStream。 Socket底层数据一次读满一个BUF_SIZE的数据，用		
			 * 	ByteArrayOutputStream 将原始byte数据组好，一次性通过	SOCKET_ENCODE编码转换为String。而BufferdInputStream是一次读
			 *  一个BUF_SIZE数据，每次通过SOCKET_ENCODE编码转换为String，可能会丢失数据。
			 * **/
				int len = 0;
				while((len = is.read(temps)) != -1) {
					bos.write(temps, 0, len);
				}
				receMsg = new String(bos.toByteArray(), ComConstant.SOCKET_ENCODE);
			}
			
		} catch (IOException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		
	}

	public void destory() {
		try {
			if (bos != null) 
				bos.close();
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			if (client != null && !client.isClosed())
				client.close();
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
	
	/**
	 * 相关子类实现，如Socket通讯内容协议是XML、String等。
	 * @param message 接收来自客户端的报文
	 * @return 回文
	 */
	public abstract String handleMessage(String message);
	
}
