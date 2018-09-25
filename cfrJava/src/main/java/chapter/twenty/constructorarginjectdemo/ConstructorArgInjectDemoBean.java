package chapter.twenty.constructorarginjectdemo;

public class ConstructorArgInjectDemoBean {
	private Integer id;
	private String name;
	public ConstructorArgInjectDemoBean(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "ConstructorArgInjectDemoBean [id=" + id + ", name=" + name + "]";
	}
}
