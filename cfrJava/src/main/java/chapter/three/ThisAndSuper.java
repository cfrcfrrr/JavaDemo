package chapter.three;

class TA {
	public TA () {
		System.out.println("TA no parameter");
	}
	public TA (String str) {
		System.out.println("TA one parameter " + str);
	}
}

class TB extends TA {
	public static String classStra = "TACLASS";
	public TB() {
		System.out.println("TB no parameter");
	}
	public TB(String str) {
		this("xxx ", str);
		System.out.println("TB one parameter " + str);
	}
	public TB(String stra, String strb) {
		System.out.println("TC two parameter " + stra + strb);
	}
}

class TC extends TB {
	private String msg = null;
	public TC () {
		this("hello ");
		System.out.println("TC no parameter");
		this.msg = "no";
	}
	public TC (String str) {
		this(str, "world ");
		System.out.println("TA one parameter " + str);
		this.msg = "one";
	}
	public TC (String stra, String strb) {
		super(stra);
		System.out.println("TA two parameter " + stra + strb);
		this.msg = "two";
	}
	@SuppressWarnings("static-access")
	public String getMsg () {
		return super.classStra + this.msg;
	}
}

public class ThisAndSuper {

	public static void main(String[] args) {
		TC tc = new TC();
		System.out.println(tc.getMsg());
	}

}