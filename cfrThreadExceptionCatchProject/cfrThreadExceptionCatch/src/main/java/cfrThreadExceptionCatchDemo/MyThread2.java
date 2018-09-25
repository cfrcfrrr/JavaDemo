package cfrThreadExceptionCatchDemo;

import java.util.concurrent.Callable;

public class MyThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("call");
        int i = 1/0;
        return i;
    }
}
