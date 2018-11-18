package cfrCallableDemo;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Object> {
    private Integer ticket = 5;
    @Override
    public Object call() throws Exception {
        while(this.ticket > 0) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread() + " sell " + this.ticket--);
        }
        return Thread.currentThread() + "over";
        // return null;
    }
}
