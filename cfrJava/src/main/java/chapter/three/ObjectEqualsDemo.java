package chapter.three;

class BookObjEqu {
	private String title;
	private double price;
	public BookObjEqu(String title, double price) {
		this.title = title;
		this.price = price;
	}
	// setter��getter��toString����

	public boolean equals(Object obj) { // 
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BookObjEqu)) { // ��Ҫ���ж��Ƿ������࣬��������ת��ʱ�ᱨCLASSCastException�쳣
			return false;
		}
		BookObjEqu book = (BookObjEqu) obj; // Object�����ȡ�����������ԣ���Ҫ����ת��
		if (this.title.equals(book.title) && this.price == book.price) {
			return true;
		} else {
			return false;
		}
	}
}
public class ObjectEqualsDemo {
	@SuppressWarnings("unlikely-arg-type")
	public static void main (String args []) {
		BookObjEqu b1 = new BookObjEqu("aaa", 11.1);
		BookObjEqu b2 = new BookObjEqu("bbb", 22.2);
		BookObjEqu b3 = new BookObjEqu("aaa", 11.1);
		BookObjEqu b4 = b1;
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b1.equals(b2)); // false
		System.out.println(b1.equals(b3)); // true
		System.out.println(b1.equals(b4)); // true
		System.out.println(b1.equals("hello")); // false
		
	}
}
