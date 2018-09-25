package threadsafetysimpledateformatanddatetimeformatdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafetySimpleDateFormatAndDateTimeFormatDemoMain {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd,HHmmss");  
        ExecutorService ts = Executors.newFixedThreadPool(100); // 线程池
        for (;;) {  
            ts.execute(new Runnable() {        
                @Override  
                public void run() {  
                    try {  
                      String format = sdf.format(new Date(Math.abs(new Random().nextLong())));  
                      System.out.println(format);  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                        System.exit(1);  
                    }  
                }  
            });  
        }  
	}
}