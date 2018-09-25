package chapter.three;

interface AObjRefType {
	void print();
}
class BObjRefType implements AObjRefType {
	public void print() {
		System.out.println("hello");
	}
}
public class ObjectRefType {
	public static void main(String args[]) {
		// Object类接收数组
		Object obj = new int[] {1,2,3};
		if (obj instanceof int[]) {
			int data [] = (int []) obj;
			for (int x = 0; x < data.length; x ++) {
				System.out.println(data[x]);
			}
		}
		// Object类接收接口对象
		AObjRefType a = new BObjRefType();
		Object obj2 = a;
		AObjRefType t = (AObjRefType) obj2;
		t.print();
		// Object类接收基础数据类型
		Object obj3 = 10;
		System.out.println(obj3);
	}
}
