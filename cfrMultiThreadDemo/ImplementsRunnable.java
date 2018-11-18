package multithreaddemo;

public class ImplementsRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("implements runnable " + Thread.currentThread().getName() + " run.");
	}
}
