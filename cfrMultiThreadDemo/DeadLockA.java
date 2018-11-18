package multithreaddemo;

public class DeadLockA {
	public synchronized void say(DeadLockB b) {
		System.out.println("a say");
		b.get();
	}

	public synchronized void get( ) {
		System.out.println("a get");
	}
}
