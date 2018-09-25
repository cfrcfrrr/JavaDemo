package chapter.four;

import java.util.Arrays;

class Bookb implements Comparable<Bookb> {
	private String title;
	private double price;
	public Bookb(String title,double price) {
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return this.title + "-" + this.price;
	}
	@Override
	public int compareTo(Bookb o) { // Arrays.sort()会自动调用此方法比较
		if(this.price > o.price) {
			return 1;
		} else if (this.price < o.price) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
public class ComparableInterfaceDemo {
	public static void main(String[] args) throws Exception {
		Bookb Bookbs [] = new Bookb[] {
				new Bookb("aaa",111),
				new Bookb("bbb",22),
				new Bookb("ccc",3333),
				new Bookb("ddd",4),
		};
		Arrays.sort(Bookbs);
		System.out.println(Arrays.toString(Bookbs));
	}
}
