package threadnosafetydemo;

public class ThreadNoSafetyDemoProcess implements Runnable {
	private int ticket = 5;
	@Override
	public void run() {
		for(int i = 0; i < 5; i ++) {
			if (ticket > 0) {
				try {
					Thread.sleep(20); // 加延迟容易使问题暴露
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " sell ticket " + ticket--); // 结果出现0就是出现问题。遗留：所以可以理解成每次要用到变量都会取内存重新取？				
			}
		}
	}
	public void reset() {
		ticket = 5;
	}
}
