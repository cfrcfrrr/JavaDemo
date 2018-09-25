package chapter.three;
public class AnonymousObject {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		String strb = new String("hello").intern();
		String stra = "hello";
		String strc = "hello";
		String strd = new String("hello");
//		System.out.println(stra == strb);
		System.out.println(stra == strc);
//		System.out.println(strb == strc);
//		System.out.println(strb == strd);
		System.out.println(stra.length());
	}

}
