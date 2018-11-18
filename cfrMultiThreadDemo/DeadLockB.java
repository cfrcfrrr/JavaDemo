package multithreaddemo;

public class DeadLockB {
	public synchronized void say(DeadLockA a) {
		System.out.println("b say");
		a.get();
	}

	public synchronized void get( ) {
		System.out.println("b get");
	}
}
