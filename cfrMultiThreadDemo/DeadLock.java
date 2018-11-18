package multithreaddemo;

public class DeadLock implements Runnable {
	private static DeadLockA deadLockA = new DeadLockA();
	private static DeadLockB deadLockB = new DeadLockB();
	
	public DeadLock() {
		new Thread(this).start();
		deadLockB.say(deadLockA);
	}
	@Override
	public void run() {
		deadLockA.say(deadLockB);
	}
}
