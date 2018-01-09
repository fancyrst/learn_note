package org.fancy.socket.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.fancy.socket.mina.handler.Demo1ClientHandler;

public class Demo1Client {

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30*1000);
		connector.getFilterChain().addLast("codec", 
					new ProtocolCodecFilter(new TextLineCodecFactory(
							Charset.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));
		
		connector.setHandler(new Demo1ClientHandler());
		
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(Constant.LOCAL_HOST, Constant.PORT));
			future.awaitUninterruptibly();
			session = future.getSession();
			session.write("bye1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.getCloseFuture().awaitUninterruptibly();
				connector.dispose();
			}
		}
	}
}
