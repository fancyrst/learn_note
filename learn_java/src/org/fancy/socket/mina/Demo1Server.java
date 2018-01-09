package org.fancy.socket.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.fancy.socket.mina.handler.Demo1ServerHandler;

public class Demo1Server {
	
	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
		acceptor.setHandler(new Demo1ServerHandler());
		try {
			acceptor.bind(new InetSocketAddress(Constant.PORT));
			System.out.println("服务端启动成功...     端口号为:"+Constant.PORT);
		} catch (IOException e) {
			acceptor.dispose();
			e.printStackTrace();
		}		
	}
}
