package chapter.four;
class Member {
	public Member() {
		System.out.println("���ù�����");
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("����������");
		throw new Exception("�׸��쳣");
	}
}
public class SystemClassDemo {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Member mem = new Member();
		mem = null;
		System.gc(); // ��Ҫ��GC�Ż������������Ż������
	}
}
