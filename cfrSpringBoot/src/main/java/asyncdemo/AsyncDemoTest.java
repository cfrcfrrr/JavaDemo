package asyncdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AsyncDemoTest {
	@Autowired
	private AsyncDemoAsync async;
//	@Test
//	public void asyncTestNoReturn() throws InterruptedException {
//		for(int i = 0; i < 5; i ++) {
//			async.asyncNoReturn();
//		}
//		Thread.sleep(10000);
//	}
	
	@Test
	public void asyncTestWithReturn() throws InterruptedException {
		for(int i = 0; i < 5; i ++) {
			async.asyncWithReturn();
		}
		Thread.sleep(10000);
	}
}
