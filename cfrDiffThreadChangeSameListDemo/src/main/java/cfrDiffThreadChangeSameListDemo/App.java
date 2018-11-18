package cfrDiffThreadChangeSameListDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 结论：多线程处理同一个列表对象，不会有什么特殊的，和单线程时一样。
 * 如果在列表遍历过程中删除、新增元素，会报ConcurrentModificationException，如果修改则没有问题。
 * 如果没有在遍历过程中，则其他线程删除、新增、修改元素都不会报ConcurrentModificationException，就是容易在逻辑上（数据上）出错，另外会低概率出现正要修改，但是被其他线程删除这种超界异常
 * 遗留问题：怎么解决数组遍历过程中新增和删除元素的异常？
 */
public class App {
    public static void main(String[] args) throws Exception {
        List<MyEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new MyEntity(i, String.valueOf(i) + i));
        }
        FutureTask futureTask1 = new FutureTask(new MyCallable(list));
        FutureTask futureTask2 = new FutureTask(new MyCallable(list));
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();
        // ExecutorService executorService = Executors.newFixedThreadPool(2);
        // executorService.execute(futureTask1); // 不阻塞
        // executorService.execute(futureTask2);
        futureTask1.get(); // 实际上异常捕获是在get()这里，如果没有调get()，即使写了个死循环，也捕获不到多线程里面的异常
        futureTask2.get();
    }
}
