package chapter.three;

public class ArraySortAndReverse {

	public static void main(String[] args) {
		// sort, java.util.Arrays.sort
		int a[] = new int[] { 23, 33, 21123, 12, 15, 92, 38, 55, 0, 100 };
//		java.util.Arrays.sort(a);
		int tmp = 0;
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a.length - 1; y++) {
				if (a[y] > a[y + 1]) {
					tmp = a[y + 1];
					a[y + 1] = a[y];
					a[y] = tmp;
				}
			}
		}
		for (int x = 0; x < a.length; x++) {
			System.out.println(a[x]);
		}
		System.out.println("------------------------------------");
		// reverse
		int b[] = new int[] { 23, 33, 21123, 12, 15, 92, 38, 55, 0, 100, 102};
		for (int x = 0; x < b.length / 2; x++) {
			tmp = b[b.length - 1 - x];
			b[b.length - 1 - x] = b[x];
			b[x] = tmp;
		}
		for (int x = 0; x < b.length; x++) {
			System.out.println(b[x]);
		}
		System.out.println("------------------------------------");
		// arraycopy
		int c [] = new int [5];
		System.arraycopy(b, 3, c, 0, 5);
		for (int x = 0; x < c.length; x++) {
			System.out.println(c[x]);
		}
	}
}
