package chapter.four;
class InfoE {
	private String name;
	private String title;
	private boolean flag = true;
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public synchronized void getInfo() {
		if (this.flag == true) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this.name + "-" + this.title);
		this.flag = true;
		super.notify();
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public synchronized void setInfo(String name, String title) {
		if (this.flag == false) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.name = name;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.title = title;
		this.flag = false;
		super.notify();
	}
}
class ProductorE implements Runnable {
	private InfoE info;
	public ProductorE(InfoE info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 30; x ++) {
			if (x % 2 == 0) {
				info.setInfo("aaa", "aaaaa");
			} else {
				info.setInfo("bbb", "bbbbb");
			}
		}
	}
}
class ConsumerE implements Runnable {
	private InfoE info;
	public ConsumerE(InfoE info) {
		this.info = info;
	}
	@Override
	public void run() {
		for (int x = 0; x < 30; x ++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			info.getInfo();
		}		
	}
}

public class ProductorsAndConsumersError {
	public static void main(String[] args) {
		InfoE info = new InfoE();
		ProductorE p = new ProductorE(info);
		new Thread(p).start();
		ConsumerE c = new ConsumerE(info);
		new Thread(c).start();
	}
}
