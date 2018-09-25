package asyncdemo;

import java.util.concurrent.Future;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

//@Configuration
@Component
@EnableAsync
public class AsyncDemoAsync {
	@Async
	public void asyncNoReturn() {
		System.out.println("Start " + Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			Thread.sleep(5000);
			System.out.println("End " + Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Async
	public Future<String> asyncWithReturn() {  
		System.out.println("Start " + Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {  
			Thread.sleep(5000);  
			return new AsyncResult<String>("End " + Thread.currentThread().getName() + " " + Thread.currentThread().getStackTrace()[1].getMethodName());  
		} catch (InterruptedException e) {  
			e.printStackTrace();
		}
		return null;  
	}
}