package cfrThreadExceptionCatchDemo;

import java.util.List;

public class MyThread implements Runnable {
    private List<String> errMsg;

    public MyThread() {}

    public MyThread(List<String> errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public void run() {
        try {
            System.out.println("thread " + Thread.currentThread().getName() + " run");
            throw new Exception(Thread.currentThread().getName() + " exception...");
        } catch (Exception e) {
            this.errMsg.add(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
