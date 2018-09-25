package chapter.three;

public class WrapperClassDemo {
	public static void main(String[] args) {
		// equals
		Integer obja = 10;
		Integer objb = 10;
		Integer objc = new Integer(10);
		System.out.println(obja == objb); // true
		System.out.println(obja == objc); // false
		System.out.println(obja.equals(objc)); //true
		// Object
		Object obj = 10;
		int temp = (Integer) obj;
		System.out.println(temp);
	}
}
