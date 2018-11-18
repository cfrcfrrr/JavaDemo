package multithreaddemo;

public class SynchronizedMethod implements Runnable {
	private static Integer ticket = 5;

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			this.sale();
		}
	}

	public synchronized void sale() {
		System.out.println("ExtendsThreadSynchronizedMethod " + Thread.currentThread().getName() + " in method.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (this.ticket >= 0) {
			System.out.println(
					"ExtendsThreadSynchronizedMethod " + Thread.currentThread().getName() + " sell " + this.ticket--);
		}
		System.out.println("ExtendsThreadSynchronizedMethod " + Thread.currentThread().getName() + " out method.");
	}
}
