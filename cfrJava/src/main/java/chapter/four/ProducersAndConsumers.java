package chapter.four;
class Info {
	private String title;
	private String content;
	private boolean flag = true ; // 为true,可生产；为false，可取出
	public synchronized void set(String title,String content) {
		if (this.flag == false) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.title = title;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.content = content;
		this.flag = false;
		super.notify();
	}
	public synchronized void get() {
		if (this.flag == true) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.title + "-" + this.content);
		this.flag = true;
		super.notify();
	}
}
class Productor implements Runnable {
	private Info info;
	public Productor(Info info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0 ; x < 100 ; x ++) {
			if (x % 2 == 0) {
				this.info.set("AAA","aaa");
			} else {
				this.info.set("BBB","bbb");
			}
		}
	}
}
class Customer implements Runnable {
	private Info info;
	public Customer(Info info) {
		this.info = info;
	}
	@Override
	public void run() {
		for(int x = 0 ; x < 100 ; x ++) {
			this.info.get();
		}
	}
}
public class ProducersAndConsumers {

	public static void main(String[] args) {
		Info info = new Info();
		new Thread(new Productor(info)).start();
		new Thread(new Customer(info)).start();
	}

}
