package org.fancy.socket.bio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 启动、停止Soceket server
 * @author 叶耀雄
 *
 */
public class SocketServlet extends HttpServlet {

	private static final long serialVersionUID = 6751599126635786150L;
	
	private DefaultSocketServer server = null;

	@Override
	public void init() throws ServletException {
		if (server == null)
			server = new DefaultSocketServer();
		
		server.start();
		super.init();
	}
	
	@Override
	public void destroy() {
		if (server != null)
			server.stopServer();
		
		super.destroy();
	}
}
