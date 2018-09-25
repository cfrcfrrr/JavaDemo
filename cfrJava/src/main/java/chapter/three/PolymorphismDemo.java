package chapter.three;

class A {
	public String show(C obj) {
		return "A and C";
	}
}

class B extends A {
}

class C extends B {
	public String show(B obj) {
		return "C and B";
	}
}

class D extends C {
}

public class PolymorphismDemo {

	public static void main(String[] args) {
		C c2 = new D();
		C c = new C();
		D d = new D();
//		System.out.println("c2.show(b) " + c2.show(b));
		System.out.println("c2.show(c) " + c2.show(c));
		System.out.println("c2.show(d) " + c2.show(d));
	}

}
