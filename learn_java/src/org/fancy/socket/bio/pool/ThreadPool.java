package org.fancy.socket.bio.pool;

import java.util.LinkedList;

/**
 * 自定义线程池
 * @author yexiong
 *
 */
public class ThreadPool extends ThreadGroup {

	private boolean isClose;// 线程池是否关闭
	
	// 将任务放在LinkedList中，LinkedList不支持同步，
	// 所以在添加任务和获取任务的方法声明中必须使用synchronized关键字
	private LinkedList<Runnable> workQueue ; //表示工作队列
	
	private static int threadPoolID; // 表示线程池ID
	
	private static int threadID; //表示工作线程ID
	
	public ThreadPool(int poolSize) {
		super("ThreadPool -- " + threadPoolID ++); // 线程组名
		setDaemon(true);
		workQueue = new LinkedList<Runnable>();
		for (int i=0; i<poolSize; i++) {
			new WorkThread().start();// 如果工作队列为空，则所有工作线程处于阻塞状态
		}		
	}
	
	public synchronized void execute (Runnable task) {
		if (isClose)
			throw new IllegalStateException("ThreadPool is closed!");
		
		if (task != null) {
			workQueue.add(task);
			notify(); // 唤醒正在getTask()方法中等待任务的工作线程
		}
	}
	
	/**
	 * 获取任务
	 * @return
	 * @throws InterruptedException
	 */
	protected synchronized Runnable getTask () throws InterruptedException {
		while (workQueue.size() == 0) {
			if (isClose)
				return null;
			wait();
		}
		return workQueue.removeFirst();
	}
	
	public synchronized void close () {
		if (!isClose) {
			isClose = true;
			workQueue.clear();
			interrupt();
		}
	}
	
	public void join () {
		synchronized (this) {
			isClose = true;
			notifyAll();
		}
		Thread[] threads = new Thread[activeCount()];
		int count = enumerate(threads);
		for (int i=0; i<count ; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.out.println("工作线程出错:" + e);
			}
		}
	}

	private class WorkThread extends Thread {
		
		public WorkThread() {
			// 加入当前的ThreadPool线程组中
			super(ThreadPool.this, "WorkThread -- " + (threadID++));
		}
		
		@Override
		public void run() {
			while (!isInterrupted()) {
				Runnable task = null;
				try {
					task = getTask();
					
				} catch (InterruptedException e) {
					System.out.println("获得任务异常：" + e.getMessage());
				}
				
				if (task == null)
					return;
				
				try {
					task.run();
				} catch (Throwable t) {
					System.out.println(t);
				}
			}			
		}
	}

}
