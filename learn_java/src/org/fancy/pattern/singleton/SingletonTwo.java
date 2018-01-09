package org.fancy.pattern.singleton;

/**
 * 单例模式第二种实现
 * @author yexiong
 */
public class SingletonTwo {

	// volatile关键词确保：当instance变量初始化成Singleton实例时，多个线程正确处理instance变量
	// volatile：JDK需1.5及以上版本
	private volatile static SingletonTwo instance = null;//必须加入volatile
	
	private SingletonTwo() {
		
	}
	
	/**
	 * 双重检查（Double-Check），Java并发编程实践（16.2)不推荐使用此种方式作单例和延迟加载，它不是一种高效的优化措施
	 * @return
	 */
	public static SingletonTwo getInstance() {
		if (instance == null) {// 检查instance，如为null则进入同步区域
			synchronized (SingletonTwo.class) {
				if (instance == null) { // 进入同步区域再检查一次，如果仍是null，才创建实例
					instance = new SingletonTwo();
				}
			}
		}			
		return instance;
	}
}
