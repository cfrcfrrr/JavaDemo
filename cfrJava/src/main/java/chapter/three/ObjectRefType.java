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
		// Object���������
		Object obj = new int[] {1,2,3};
		if (obj instanceof int[]) {
			int data [] = (int []) obj;
			for (int x = 0; x < data.length; x ++) {
				System.out.println(data[x]);
			}
		}
		// Object����սӿڶ���
		AObjRefType a = new BObjRefType();
		Object obj2 = a;
		AObjRefType t = (AObjRefType) obj2;
		t.print();
		// Object����ջ�����������
		Object obj3 = 10;
		System.out.println(obj3);
	}
}
