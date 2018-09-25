package chapter.three;
interface IMessage {
	public void print() ;
	default void fun() {
		System.out.println("aaa");
	}
	static void get() {
		System.out.println("bbb");
	}
}
class MessageImpl implements IMessage {
	@Override
	public void print() {
		System.out.println("ccc");
	}
}
public class InterfaceEnhanceDemo {

	public static void main(String[] args) {
		IMessage msg = new MessageImpl();
		msg.fun();
		IMessage.get();
	}
}