package threadnosafetydemo;

public class ThreadNoSafetyDemoMain extends Thread {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i ++) {
			System.out.println("-----------" + i + "-----------");
			ThreadNoSafetyDemoProcess process = new ThreadNoSafetyDemoProcess();
			process.reset();
			new Thread(process).start();
			new Thread(process).start();
			Thread.sleep(300);
		}
	}
}