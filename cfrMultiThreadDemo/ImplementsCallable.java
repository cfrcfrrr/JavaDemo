package multithreaddemo;

import java.util.concurrent.Callable;

public class ImplementsCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("implements callable " + Thread.currentThread().getName() + " call.");
		Thread.sleep(2000);
		return "success";
	}
}
