package chapter.four;

import java.util.Arrays;

public class ArraysClassDemo {
	public static void main(String[] args) {
		// sort
		int data [] = new int [] {1,5,6,2,3,4,9,8,7,10};
		Arrays.sort(data);
		System.out.println(Arrays.binarySearch(data, 9));
		// equals
		int dataA [] = new int [] {1,2,3};
		int dataB [] = new int [] {2,1,3};
		System.out.println(Arrays.equals(dataA, dataB));
		// fill
		int dataC [] = new int [10];
		Arrays.fill(dataC, 1);
		// toString
		System.out.println(Arrays.toString(dataC));
	}
}
