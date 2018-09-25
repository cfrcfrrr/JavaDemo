package chapter.three;

class Member {
	private int mid;
	private String name;
	private Car car;

	public Member(int mid, String name) {
		this.mid = mid;
		this.name = name;
	}

	public String getInfo() {
		return "编码： " + this.mid + "，姓名： " + this.name;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Car getCar() {
		return this.car;
	}
}

class Car {
	private Member member;
	private String pname;

	public Car(String pname) {
		this.pname = pname;
	}

	public String getInfo() {
		return "汽车名： " + this.pname;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return this.member;
	}
}

public class ObjectReference {
	public static void main(String[] args) {
		Member m1 = new Member(1, "马云");
		Car c1 = new Car("宝马");
		m1.setCar(c1);
		c1.setMember(m1);
		System.out.println(m1.getCar().getInfo());
		System.out.println(c1.getMember().getInfo());
	}
}