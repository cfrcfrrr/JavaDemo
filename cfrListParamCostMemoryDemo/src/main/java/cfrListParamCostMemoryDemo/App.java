package cfrListParamCostMemoryDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 结论：对象数据（数组、字符串列表、对象列表等）作为参数传递时均不会消耗内存
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 + "KB"); // 185456KB
        int size = 100000;
        int [] intArray = new int[size];
        List<String> stringList = new ArrayList<>();
        List<MyEntity> myEntityList = new ArrayList<>();
        for(int i = 0; i < size; i ++) {
            intArray[i] = i;
            stringList.add(String.valueOf(i));
            myEntityList.add(new MyEntity(i, String.valueOf(i)));
        }
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 + "KB"); // 167402KB
        for(int i = 0; i < 10; i ++) {
            FutureTask futureTask = new FutureTask(new Callable() {
                @Override
                public Object call() throws Exception {
                    myMethod(intArray, stringList, myEntityList);
                    return null;
                }
            });
            new Thread(futureTask).start();
            System.out.println(Runtime.getRuntime().freeMemory() / 1024 + "KB"); // 167402KB
        }
    }

    public static void myMethod(int [] intArray, List<String> stringList, List<MyEntity> myEntityList) throws Exception {
        Thread.sleep(10000);
        System.out.println("over");
    }
}
