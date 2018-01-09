package org.fancy.socket.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class Demo1ServerHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("exception message:" + cause.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		System.out.println("服务端接收到的数据为：" + msg);
		if ("bye".equals(msg)) { // 服务端断开连接的条件
			session.close(false);
		}
		session.write("hello");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.close(false);
		System.out.println("服务端发送信息成功..."+ message.toString());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("服务端与客户端创建连接...");
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("服务端进入空闲状态...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("服务端与客户端连接打开...");
		super.sessionOpened(session);
	}

	

}
