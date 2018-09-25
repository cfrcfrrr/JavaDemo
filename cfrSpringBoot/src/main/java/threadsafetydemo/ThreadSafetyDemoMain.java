package threadsafetydemo;

public class ThreadSafetyDemoMain extends Thread {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i ++) {
			System.out.println("-----------" + i + "-----------");
			ThreadSafetyDemoProcess process = new ThreadSafetyDemoProcess();
			process.reset();
			new Thread(process).start();
			new Thread(process).start();
			Thread.sleep(300);
		}
	}
}