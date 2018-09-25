package chapter.four;
class Book implements Cloneable {
	private String title;
	private double price;
	public Book(String title, double price) {
		this.setTitle(title);
		this.setPrice(price);
	}
	@Override
	public String toString() {
		return this.getTitle() + "-" + this.getPrice();
	}
	// 此类需要对象克隆操作，才需要进行方法的覆写
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		try {
			this.title = title;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
public class CloneDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
		Book bka = new Book("aaa", 11.1);
		Book bkb = (Book)bka.clone();
		System.out.println(bka);
		System.out.println(bkb);
		// 克隆，而不是引用，修改原对象，克隆对象不变
		bka.setTitle("ccc");
		System.out.println(bka);
		System.out.println(bkb);
	}

}
