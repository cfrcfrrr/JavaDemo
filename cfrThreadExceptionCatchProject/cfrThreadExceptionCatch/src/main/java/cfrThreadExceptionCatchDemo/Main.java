package cfrThreadExceptionCatchDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 遗留问题：
 * （已解决）1、虽然在异常处理器中捕获到了，但是处理器中也抛不出，但是在调用子线程的地方还是捕获不到，怎么处理？——使用线程内部属性，外层调用判断属性是否为空——但是使用返回值外层需要等待，这样多线程就没有意义了啊？——使用Callable创建线程就可以抛出异常
 * （已解决）2、另外在线程中只能抛出RuntimeException？不能抛出Exception吗？——在run()中加一个try...catch...捕捉到异常后用RuntimeException抛出
 * 3、如果使用线程池，怎么自定义线程名？
 * 4、awaitTermination()的阻塞怎么理解？此时是可以继续提交任务的。
 * 5、使用返回值方法时，如果外层不用线程池，只new一个线程，怎么等待？
 */
public class Main {
    public static void main(String[] args) {
        try {
            // // 方法一：在创建线程时绑定异常处理器
            // MyThread myThread = new MyThread();
            // Thread thread = new Thread(myThread,"子线程1");
            // thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            // thread.start();
            // // 方法二：在线程工厂绑定异常处理器，使用线程工厂创建线程池
            // ExecutorService executorService = Executors.newCachedThreadPool(new MyThreadFactory());
            // executorService.execute(new MyThread());
            // // 方法三：定义一个默认的异常处理器
            // Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            // ExecutorService executorService1 = Executors.newCachedThreadPool();
            // executorService1.execute(new MyThread());
            // // 试了一下默认异常处理器在其他类也是生效的
            // new NewThreadNotInMain().test();
            // // 方法四：根据返回值捕获
            // List<String> errMsg = new ArrayList<>(); // 注意这里需要是个类，要能使线程中修改的值也改到外面的值，注意String类是特殊的，用String也不行
            // ExecutorService executorService2 = Executors.newFixedThreadPool(1);
            // executorService2.execute(new MyThread(errMsg));
            // executorService2.shutdown(); // shutdown()方法，将线程池状态设置为SHUTDOWN，停止接收新提交任务，但是已提交任务（正在运行和等待的）会执行完，全都执行完后才真正停止
            // executorService2.awaitTermination(1, TimeUnit.DAYS); // awaitTermination()方法，将当前线程阻塞，直到已提交的任务执行完，或达到超时时间，或线程抛出InterruptedException
            // if(errMsg.size() > 0) {
            //     System.out.println("根据返回值捕获" + errMsg);
            //     throw new Exception(errMsg.toString());
            // }
            // 方法五：使用Callable创建线程，可以抛出异常（最好的解决办法）
            FutureTask futureTask = new FutureTask(new MyThread2());
            new Thread(futureTask).start();
            System.out.println(futureTask.get()); // 调用get()方法会阻塞主线程
            System.out.println("end.");
        } catch (Exception e) {
            System.out.println("main catch " + e.getMessage());
        }
    }
}
