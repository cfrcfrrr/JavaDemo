package multithreaddemo;

public class ExtendsThread extends Thread {
	private static Integer ticket = 5;
	
	ExtendsThread() {
		super();
	}
	
//	这里如果设置一个对象变量，再用this.name = name;去设置是不可以的
	ExtendsThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(ticket >= 0) {
//			注意休眠时间是不精确的
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("ExtendsThead " + Thread.currentThread().getName() + " sell " + ticket --);
		}
	}
}
