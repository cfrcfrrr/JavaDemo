package chapter.three;

interface Subject {
	public void make();
}
class RealSubject implements Subject {
	public void make() {
		System.out.println("真实主题操作");
	}
}
class ProxySubject implements Subject {
	private Subject subject;
	public ProxySubject(Subject subject) {
		this.subject = subject;
	}
	public void prepare() {
		System.out.println("代理负责做准备");
	}
	public void make() {
		this.prepare();
		this.subject.make();
		this.destory();
	}
	public void destory() {
		System.out.println("代理负责收尾");
	}
}
public class ProxyDemo {
	public static void main(String[] args) {
		Subject sub = new ProxySubject(new RealSubject());
		sub.make();
	}
}
