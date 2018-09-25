package chapter.four;

import java.io.IOException;

public class RuntimeDemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime run = Runtime.getRuntime();
		System.out.println(run.maxMemory());
		System.out.println(run.totalMemory());
		System.out.println(run.freeMemory());
		
		//²úÉúÀ¬»ø
		String str = "aaa";
		for(int x = 0 ; x < 1000 ; x ++) {
			str = str + x;
		}
		System.out.println(run.maxMemory());
		System.out.println(run.totalMemory());
		System.out.println(run.freeMemory());
		
		run.gc();
		System.out.println(run.maxMemory());
		System.out.println(run.totalMemory());
		System.out.println(run.freeMemory());
		
		Process pro = run.exec("mspaint.exe");
		Thread.sleep(10000);
		pro.destroy();
	}

}
