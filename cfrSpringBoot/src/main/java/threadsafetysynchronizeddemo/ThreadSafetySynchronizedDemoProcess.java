package threadsafetysynchronizeddemo;

public class ThreadSafetySynchronizedDemoProcess {
	private int a = 3;
	private int b = 3;
	private int c = 3;
	private static int sa = 3;
	private static int sb = 3;
	private static int sc = 3;

	// 同步对象方法
	public synchronized void changeA() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " change a " + a--);
		}
	}

	// 同步块，锁定对象
	public void changeB() {
		synchronized (this) {
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " change b " + b--);
			}
		}
	}

	// 普通对象方法
	public void changeC() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " change c " + c--); // 这里改成a或b也都可以访问，锁的是方法和代码块，而不是对象或属性
		}
	}

	// 同步类方法
	public synchronized static void changeSA() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " change sa " + sa--);
		}
	}

	// 同步块，锁定类
	public static void changeSB() {
		for (int i = 0; i < 3; i++) {
			synchronized (ThreadSafetySynchronizedDemoProcess.class) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " change sb " + sb--);
			}
		}
	}

	// 普通类方法
	public static void changeSC() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " change sc " + sc--);
		}
	}
}
