package reflectdemo;

public class MyClass {
	private String name;
	private double score;
	public MyClass(String name, double score) {
		this.name = name;
		this.score = score;
	}
	public MyClass() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "MyClass [name=" + name + ", score=" + score + "]";
	}
}
