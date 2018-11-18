package multithreaddemo;

public class SynchronizedBlock implements Runnable{
	private static Integer ticket = 5;

	
	@Override
	public void run() {
		for(int i = 0; i < 5; i ++) {
			synchronized (this) {
				System.out.println("ExtendsThreadSynchronizedBlock" + Thread.currentThread().getName() + " in synchronized block.");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(this.ticket >= 0) {
					System.out.println("ExtendsThreadSynchronizedBlock " + Thread.currentThread().getName() + " sell " + this.ticket --);
				}
				System.out.println("ExtendsThreadSynchronizedBlock" + Thread.currentThread().getName() + " out synchronized block.");
			}
		}
	}
	
}
