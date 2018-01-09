package org.fancy.pattern.singleton;

/**
 * 单例模式第一种实现
 * @author yexiong
 */
public class SingletonOne {

	private static SingletonOne instance = null;
	
	private SingletonOne() {
		
	}
	
	/**
	 * 在多线程的环境下，可能存在产生多例的情况
	 * @return
	 
	public static SingletonOne getInstance() {
		if(instance == null) 
			instance = new SingletonOne();
		
		return instance;
	}
	*/
	
	/**
	 * 在多线程的环境下，可能存在产生多例的情况
	 * 加入 synchronized关键字解决多线程环境下的问题。
	 * 缺点：只有第一次执行此方法时，才需真正需要同步。而往后每一次的同步就是多余的，消耗性能
	 * @return
	 */
	public static synchronized SingletonOne getInstance() {
		if(instance == null) 
			instance = new SingletonOne();
		
		return instance;
	}
}
