package org.fancy.pattern.factory.method;

import java.util.ArrayList;
import java.util.List;

import org.fancy.pattern.factory.xml.JavaNode;

/**
 * 交易基类：工厂方法实现
 * @author yexiong
 */
public abstract class TxBase {

	/** 封装变化：由工厂来决定实现哪个子类  **/
	protected abstract JavaNode getTxNode(String txCode) throws Exception;
	
	public void init() throws Exception {
		System.out.println("Call TxBase init method");
	}
	
	/**
	 * 
	 * @param txCode
	 * @return
	 * @throws Exception
	 */
	public JavaNode getInfoNode(String txCode) throws Exception {
		if(txCode == null || "".equals(txCode))
			return null;
		init();		
		List<JavaNode> childNodes = new ArrayList<JavaNode>();
		childNodes.add(getTxNode(txCode));
		return new JavaNode("Info", childNodes);
	}
}
