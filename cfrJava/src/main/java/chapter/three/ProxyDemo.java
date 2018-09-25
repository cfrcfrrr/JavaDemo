package chapter.three;

interface Subject {
	public void make();
}
class RealSubject implements Subject {
	public void make() {
		System.out.println("��ʵ�������");
	}
}
class ProxySubject implements Subject {
	private Subject subject;
	public ProxySubject(Subject subject) {
		this.subject = subject;
	}
	public void prepare() {
		System.out.println("��������׼��");
	}
	public void make() {
		this.prepare();
		this.subject.make();
		this.destory();
	}
	public void destory() {
		System.out.println("��������β");
	}
}
public class ProxyDemo {
	public static void main(String[] args) {
		Subject sub = new ProxySubject(new RealSubject());
		sub.make();
	}
}
