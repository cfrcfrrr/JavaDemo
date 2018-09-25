package chapter.three;

interface Message {
	public void print();
}
public class AnonymousInnerClassDemo {
	public static void main(String[] args) {
		fun(new Message() {
			public void print() {
				System.out.println("hello world");
			}
		});
	}
	public static void fun(Message msg) {
		msg.print();
	}
}
