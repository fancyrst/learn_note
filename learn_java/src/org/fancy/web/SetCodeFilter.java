package org.fancy.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCodeFilter implements Filter {
	
	private String encoding = "UTF-8";	

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void destroy() {}

	/**
	 * 这种方式只能处理POST提交的编码问题。GET方式需通过URLEncoder形式处理
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		filterchain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
