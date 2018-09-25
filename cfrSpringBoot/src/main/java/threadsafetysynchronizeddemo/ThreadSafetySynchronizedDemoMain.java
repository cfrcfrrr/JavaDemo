package threadsafetysynchronizeddemo;

public class ThreadSafetySynchronizedDemoMain extends Thread {
	public static void main(String[] args) throws InterruptedException {
		ThreadSafetySynchronizedDemoProcess processA = new ThreadSafetySynchronizedDemoProcess();
		/**
		 * 同步方法和同步块会相互影响，当一个线程进入同步对象方法或同步对象块时，获取这个对象的对象锁，其他线程不能访问这个对象的所有同步对象方法和同步对象块<br>
		 * 另外线程的执行顺序不一定，虽然线程AA先调，AB后调，但可能AB先执行，AA后执行，所以结果会为AA先执行完，再执行AB，或者反过来<br>
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				processA.changeA();
			}
		}, "threadAA").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				processA.changeB();
			}
		}, "threadAB").start();
		
		/*
		 * synchronized不影响非synchronized方法，可以同时进入，甚至可访问同一属性
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				processA.changeC();
			}
		}, "threadAC").start();
		/*
		 * 对象锁不影响其他对象，也就是不同对象的同步方法或同步块可被不同线程同时访问
		 */
		ThreadSafetySynchronizedDemoProcess processB = new ThreadSafetySynchronizedDemoProcess();
		new Thread(new Runnable() {
			@Override
			public void run() {
				processB.changeA();
			}
		}, "threadBA").start();
		
		/**
		 * 对象锁和类锁相互不影响，也就是对象的同步方法或块，可以和类的同步方法或块被不同线程同时访问<br>
		 * 类锁类似于对象锁
		 * 类的同步方法和同步块，相互影响，当一个线程进入同步类方法或同步类块时，获取这个类的类锁，其他线程不能访问这个类的所有同步类方法和同步类块
		 * 但普通类方法不受影响
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadSafetySynchronizedDemoProcess.changeSA();
			}
		}, "threadSA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadSafetySynchronizedDemoProcess.changeSB();
			}
		}, "threadSB").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadSafetySynchronizedDemoProcess.changeSC();
			}
		}, "threadSC").start();
	}
}