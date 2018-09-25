package threadsafetystringbuilderandstringbufferdemo;

public class ThreadSafetyStringBuilderAndStringBufferDemoMain extends Thread {
	public static void main(String[] args) throws InterruptedException {
		ThreadSafetyStringBuilderAndStringBufferDemoProcess process = new ThreadSafetyStringBuilderAndStringBufferDemoProcess();
		for (int i = 0; i < 3000; i++) { // 出问题概率低，要多试几次。遗留：貌似刚启动的时候出问题概率高，可能吗？什么原因？
			if (i % 10 == 0) {
				System.out.print(i + " ");
			}
			process.setBuilder(new StringBuilder());
			process.setBuffer(new StringBuffer());
			new Thread(new Runnable() {
				@Override
				public void run() {
					process.addA();
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					process.addB();
				}
			}).start();
			Thread.sleep(50);
			if (process.getBuilder().length() != 10) {
				Thread.sleep(1000); // 因为有可能是还没修改完成就去取，所以在初次判断有问题后隔一秒再去判断一次，有问题再报错
				if (process.getBuilder().length() != 10) {
					System.out.println("\nStringBuilder error in " + i + " times, result is " + process.getBuilder());
				}
			}
			if (process.getBuffer().length() != 10) {
				Thread.sleep(1000);
				if (process.getBuffer().length() != 10) {
					System.out.println("\nStringBuffer error in " + i + " times, result is " + process.getBuffer());
				}
			}
			Thread.sleep(50);
		}
	}
}