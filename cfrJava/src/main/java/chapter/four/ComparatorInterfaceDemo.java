package chapter.four;

import java.util.Arrays;
import java.util.Comparator;

class Bookd {
	private String title;
	private double price;
	public Bookd(String title,double price) {
		this.title = title;
		this.setPrice(price);
	}
	@Override
	public String toString() {
		return this.title + "-" + this.getPrice();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
class BookdCompatator implements Comparator<Bookd> {
	@Override
	public int compare(Bookd o1, Bookd o2) {
		if (o1.getPrice() < o2.getPrice()) {
			return 1;
		} else if (o1.getPrice() > o2.getPrice()) {
			return -1;
		} else {
			return 0;
		}
	}
}
public class ComparatorInterfaceDemo {
	public static void main(String[] args) {
		Bookd books [] = new Bookd [] {
				new Bookd("aaa",111),
				new Bookd("bbb",22),
				new Bookd("ccc",3333),
				new Bookd("ddd",4)
		};
		Arrays.sort(books, new BookdCompatator());
		System.out.println(Arrays.toString(books));
	}
}
