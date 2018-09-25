package threadsafetydemo;

public class ThreadSafetyDemoProcess implements Runnable {
	private int ticket = 5;

	/*
	 * 设置同步方法或同步代码块都可以解决问题<br>
	 * 因为同步方法锁住的是整个方法，更影响效率，而同步块可以根据需要只锁定一部分
	 */
	@Override
//	public synchronized void run() { // 方法一：同步方法，获得这个对象的对象锁，也就是其他线程进不到这个方法中
	public void run() {
		for (int i = 0; i < 5; i++) {
			synchronized (this) { // 方法二：同步代码块，获得这个对象的对象锁，其他线程进不到这个代码块中
				if (ticket > 0) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " sell ticket " + ticket--);
				}
			}
		}
	}

	public void reset() {
		ticket = 5;
	}
}
