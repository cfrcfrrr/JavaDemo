package variableparametersdemo;

public class App {
	public static void main(String[] args) {
		print1(new int[] {1, 2, 3});
		print1(new int[] {1, 2});
		print1(1, 2, 3);
		print1(1, 2);
		print1();
		
		print2(new int[] {1, 2, 3});
		print2(new int[] {1, 2});
//		print2(1, 2, 3); // 编译错误
//		print2(1, 2); // 编译错误
//		print2(); // 编译错误
	}
	
//	只有最后一个参数能用作可变参数
	public static void print1(int ... data) {
		for(int i : data) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static void print2(int [] data) {
		for(int i : data) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
