package chapter.four;
class Booke{
	public Booke() {
		System.out.println("aaa");
	}
}
public class NewInstanceMethodDemo {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> cls = Class.forName("chapter.four.Booke");
		Object obj = cls.newInstance(); // ������Object���գ���������ת��
	}
}
