package cfrThreadExceptionCatchDemo;

public class NewThreadNotInMain {
    public void test() {
        new Thread(new MyThread()).start();
    }
}
