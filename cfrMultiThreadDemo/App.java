package multithreaddemo;

import java.util.concurrent.FutureTask;

public class App {
	public static void main(String[] args) throws Exception {
//		一、Thread
		// 不建议不同线程设置相同名称，以及对已启用线程更名；
		// 如果没有设置，则默认为“Thread-X”,X为已创建的线程，从0开始
		ExtendsThread extendsThread1 = new ExtendsThread("线程A");
		ExtendsThread extendsThread2 = new ExtendsThread("线程B");
		// 优先级越高，越有可能先执行，注意只是有可能
		// MAX_PRIORITY:最高，10；NORM_PRIORITY:中等，5，默认值；MIN_PRIORITY:最低，1；
		extendsThread1.setPriority(Thread.MAX_PRIORITY);
		System.out.println("default priority is " + extendsThread2.getPriority());
		// 这里多线程访问同一资源时，会有异步问题，ticket最后可能变为负数		
		extendsThread1.start();
		extendsThread2.start();
		
//		二、Runnable，Runnable接口没有单继承局限
		ImplementsRunnable implementsRunnable = new ImplementsRunnable();
		new Thread(implementsRunnable, "线程C").start();
		
//		三、Callable，Callable接口有返回值，且可以捕获异常
		ImplementsCallable implementsCallable = new ImplementsCallable();
		FutureTask<String> futureTask = new FutureTask<>(implementsCallable);
		new Thread(futureTask, "线程D").start();
		try {
			System.out.println(futureTask.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Callable get() will block main thread.");
		
//		同步：多个操作在同一时间段内只能有一个线程进行，其他线程要等待此线程完成之后，才可以继续执
//		多个线程访问同一资源时必须要同步操作
		
//		四、同步方法处理，解决同步问题
//		一个线程进入一个实例的任何同步方法,别的线程不能进入该实例的其他同步方法,但可进入非同步方法
		SynchronizedMethod extendsThreadSynchronizedMethod = new SynchronizedMethod();
		new Thread(extendsThreadSynchronizedMethod, "线程E").start();
		new Thread(extendsThreadSynchronizedMethod, "线程F").start();
		
		Thread.sleep(6000);
		
//		五、同步块处理，解决异步问题(使用同步方法会锁住整个方法,效率较低,通常只使用同步块锁住关键部分)
		SynchronizedBlock extendsThreadSynchronizedBlock = new SynchronizedBlock();
		new Thread(extendsThreadSynchronizedBlock, "线程G").start();
		new Thread(extendsThreadSynchronizedBlock, "线程H").start();
		
		Thread.sleep(6000);
		
//		六、死锁，同步过多时，可能因逻辑错误导致死锁，不过不容易出现
		// 整个例子多试几次就会出问题		
		new DeadLock();
	}
}
