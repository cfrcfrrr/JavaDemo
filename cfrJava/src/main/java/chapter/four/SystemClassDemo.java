package chapter.four;
class Member {
	public Member() {
		System.out.println("调用构造了");
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("调用析构了");
		throw new Exception("抛个异常");
	}
}
public class SystemClassDemo {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Member mem = new Member();
		mem = null;
		System.gc(); // 需要调GC才会清理垃圾，才会调析构
	}
}
