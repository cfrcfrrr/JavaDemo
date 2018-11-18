package cfrCallableDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 结论：
 * 按我的理解，FutureTask通常直接使用new Thread(futureTask).start()执行即可，使用ExecutorService执行则是用了线程池
 * <p>
 * 另外，FutureTask有几个方法
 * cancel()：取消任务
 * isCancelled()：任务是否被成功取消
 * isDone()：任务是否完成
 * get()：获取返回值，会阻塞主线程
 * get(long timeout, TimeUnit unit)：获取返回值，设定了等待的超时时间和间隔获取时间
 */
public class App {
    public static void main(String[] args) throws Exception {
        // 一、Callable FutureTask Thread
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable); // 遗留问题：要测试Callable是不是线程安全的，应该是拿一个futureTask，还是同一个callable的不同futureTask？如果是同一个futureTask，也就是当前的案例来看，callable是线程安全的，一直被第一个线程占用着
        // FutureTask futureTask2 = new FutureTask(myCallable);
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        System.out.println("此时未阻塞，调用get()时才阻塞");
        // new Thread(futureTask1).start();
        // new Thread(futureTask2).start();
        // futureTask1.get();
        futureTask.get(); // 阻塞

        // 二、Callable FutureTask ExecutorService singleThread
        ExecutorService executorServiceSingle = Executors.newSingleThreadExecutor();
        FutureTask futureTaskSingle = new FutureTask(new MyCallable());
        executorServiceSingle.execute(futureTaskSingle); // 未阻塞
        System.out.println("此时未阻塞，调用get()时才阻塞");
        futureTaskSingle.get(); // 阻塞
        executorServiceSingle.shutdown();

        // 三、Callable FutureTask ExecutorService threadPool
        ExecutorService executorServicePool = Executors.newFixedThreadPool(2);
        // List<MyCallable> myCallableList = new ArrayList<>();
        // myCallableList.add(new MyCallable());
        // myCallableList.add(new MyCallable());
        // List<Future<Object>> results = executorServicePool.invokeAll(myCallableList); // 遗留问题：如果要使用这种方法，线程类实现接口时必须声明泛型类型，例public class MyCallable implements Callable<Object>，如果没有<Object>会报错，为什么？
        List<Future<Object>> results = executorServicePool.invokeAll(Arrays.asList(new MyCallable(), new MyCallable())); // 阻塞
        System.out.println("此时已阻塞，而不是调用get()时才阻塞");
        executorServicePool.shutdown();
        for (Future<Object> result : results) {
            System.out.println(result.get());
        }

        System.out.println("over");
    }
}

